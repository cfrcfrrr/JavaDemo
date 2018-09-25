#-*-coding:gbk-*-
'''
Created on 2017��11��25��

@author: cwx457048
11:30
nowPer = min(max(min(nowPer + 1, RUN_PER_LIST[runPerNowStep]), RUN_PER_LIST[runPerNowStep-1]), 99)

'''
import sys, re, logging, openpyxl, time, os, random
from PyQt5.QtWidgets import QWidget, QDesktopWidget, QApplication, QLabel,\
    QLineEdit, QGridLayout, QPushButton, QProgressBar, QTextEdit, QTextBrowser,\
    QMessageBox, QHBoxLayout, QVBoxLayout, QGroupBox
from PyQt5.QtCore import QRegExp, QThread, QTimer, QBasicTimer, pyqtSignal, QObject
from PyQt5.QtGui import QRegExpValidator, QColor, QIcon, QTextCursor
from PyQt5 import Qt
from time import sleep

logging.basicConfig(level=logging.INFO,
                format='%(asctime)s-%(levelname)s-%(funcName)s- %(message)s',
                filename='run.log',
                filemode='w')

consoleLog = logging.StreamHandler()
consoleLog.setLevel(logging.WARNING)
consoleLogForm = logging.Formatter('%(asctime)s-%(levelname)s-%(funcName)s- %(message)s')
consoleLog.setFormatter(consoleLogForm)
logging.getLogger('').addHandler(consoleLog)

topFatherBomcode = ""
customBarcode = ""
customMac = ""
excelFileName = ""
warnMsg = ""
runPerNowStep = 1
ctrl0303Bom = []

BOMCODE_RE = "^[0-9a-zA-Z]{8}(-[0-9]{3})?$"
CUSTOM_BARCODE_RE = "^[0-9a-zA-Z][1-9a-cA-C][0-9]{4}$"
CUSTOM_MAC_RE = "^[0-9a-fA-F][02468aceACE][0-9a-fA-F]{8}$"
BOM_MESSAGE_NUM = 6
NEED_BOM_TUPLE = ('0302', '0303', '0305', '0230', '0231', '0235', '0502', '0501', '410', '0620', '0601')
CUT_303_BOM_TUPLE = ('0302', '0302')
ADD_21_BOM_TUPLE = ('0305', '0230', '0235', '0231')
Mac_NUM = 4
RUN_PER_LIST = [0, 80, 100]

class CreateGetBomGui(QWidget):
    '''
    PDM GUI Class
    '''
    
    def __init__(self):
        super(CreateGetBomGui, self).__init__()
        
        #������������������а�ť�ĵ���źŰ���һ��
        self.__createWidget()
        
        #���ò���
        self.__createLayout()
        
        #�����ť��ʼ����
        self.__runBt.clicked.connect(self.run)
        
        #��ʾ����
        self.resize(600, 600*0.618)
        self.__moveCenter()
        self.setWindowTitle('��ȡPDM���빤��')
        self.setWindowIcon(QIcon('PDM.jpg'))
        self.__filepath = os.path.split(os.path.realpath(__file__))[0]
        
        sys.stdout = EmittingStream(textWritten=self.outputWritten)  
        sys.stderr = EmittingStream(textWritten=self.outputWritten)
        
        self.show()
        
    def outputWritten(self, text):  
        cursor = self.__progressTb.textCursor()  
        cursor.movePosition(QTextCursor.End)  
        cursor.insertText(text)  
        self.__progressTb.setTextCursor(cursor)  
        self.__progressTb.ensureCursorVisible()
        
    def run(self):
        #����У��
        if not re.fullmatch(BOMCODE_RE, self.__bomcodeLe.text()):
            QMessageBox.information(self, "�������", self.__bomcodeTip + "\n��ǰ���룺" + str(self.__bomcodeLe.text()), QMessageBox.Yes)
            self.__bomcodeLe.setFocus()
            self.__bomcodeLe.selectAll()
        elif self.__barcodeLe.text() and not re.fullmatch(CUSTOM_BARCODE_RE, self.__barcodeLe.text()):
            QMessageBox.information(self, "�������", self.__barcodeTip + "\n��ǰ���룺" + str(self.__barcodeLe.text()), QMessageBox.Yes)
            self.__barcodeLe.setFocus()
            self.__barcodeLe.selectAll()
        elif self.__macLe.text() and not re.fullmatch(CUSTOM_MAC_RE, self.__macLe.text()):
            QMessageBox.information(self, "�������", self.__macTip + "\n��ǰ���룺" + str(self.__macLe.text()), QMessageBox.Yes)
            self.__macLe.setFocus()
            self.__macLe.selectAll()
#         elif not self.__barcodeLe.text():
#             QMessageBox.question(self, "����澯", "��������Ϊ�գ�����������XXX���Ƿ����", QMessageBox.Yes)
        
        global topFatherBomcode
        topFatherBomcode = ""
        topFatherBomcode = self.__bomcodeLe.text()
        
        global customBarcode
        customBarcode = ""
        customBarcode = self.__barcodeLe.text()
        
        global customMac
        customMac = ""
        customMac = self.__macLe.text()
        
        global runPerNowStep
        runPerNowStep = 1
        
        global warnMsg
        warnMsg = ""
        
        global excelFileName
        excelFileName = topFatherBomcode + "-" + time.strftime('%Y-%m-%d_%H-%M-%S', time.localtime(time.time())) + '.xls'
        
        #�����߼��̣߳����߳����ڲ�������
        runLogicChildTh = GetBomThread()
        runLogicChildTh.start() #���߳�����ȸ��߳���������ᵼ�±���
        
        global RUN_PER_LIST
        nowPer = 0
        while RUN_PER_LIST[runPerNowStep-1] != 100:
            if runPerNowStep == 1:
                self.__progressLb.setText("��ȡ������...")
            elif runPerNowStep == 2:
                self.__progressLb.setText("���ɱ����...")
            nowPer = min(max(min(nowPer + 1, RUN_PER_LIST[runPerNowStep]), RUN_PER_LIST[runPerNowStep-1]), 99)
            self.__progressPb.setValue(nowPer)
            sleep(random.uniform(0, 0.5))
        self.__progressPb.setValue(100)
        
        if warnMsg:
            self.__progressLb.setStyleSheet("color:red")
            self.__progressLb.setText("���н�����ע�⣺" + warnMsg + ")")
        else:
            self.__progressLb.setStyleSheet("color:green")
            self.__progressLb.setText("���гɹ�")
        
        self.__fileLe.setText(self.__filepath + "\\" + excelFileName)
        
    def __createWidget(self):
        self.__bomcodeLb = QLabel('���룺')
        self.__bomcodeTip = "����Ӧ����8λ���ֻ���ĸ����ϣ����ٽ�'-'��3λ���֣���02351MFF��02310UWW-001��"
        self.__bomcodeLb.setToolTip(self.__bomcodeTip)
        self.__bomcodeLb.setFixedSize(30, 20)
        
        self.__bomcodeLe = QLineEdit()
        self.__bomcodeLe.setPlaceholderText("����8��12λ����")
        self.__bomcodeLe.setToolTip(self.__bomcodeTip)
        self.__bomcodeLe.setValidator(QRegExpValidator(QRegExp(BOMCODE_RE)))
        self.__bomcodeLe.setFixedSize(100, 20)
        self.__bomcodeLe.textChanged.connect(self.__bomcodeLeEditUpper)
#         self.__bomcodeLe.editingFinished.connect(self.__bomcodeLeInputCheck) #����setValidator���Ͳ���Ч��
        
        self.__barcodeLb = QLabel('���룺024MQG10')
        self.__barcodeTip = "�����һλΪ���ֻ���ĸ���ڶ�λΪ1-9�����ֻ�A-C����ĸ������λΪ���֣���F11125��"
        self.__barcodeLb.setToolTip(self.__barcodeTip)

        self.__barcodeLe = QLineEdit()
        self.__barcodeLe.setPlaceholderText("����6λ����")
        self.__barcodeLe.setToolTip(self.__barcodeTip)
        self.__barcodeLe.setValidator(QRegExpValidator(QRegExp(CUSTOM_BARCODE_RE)))
        self.__barcodeLe.setFixedSize(80, 20)
        self.__barcodeLe.textChanged.connect(self.__barcodeLeEditUpper)
        
        self.__barcodeScriptPartLb = QLabel('01')
        
        self.__macLb = QLabel('MAC��')
        self.__macTip = "MACӦ����10λ0-9A-F����ϣ��ҵڶ�λΪż����0248ACE������2017112501��"
        self.__macLb.setToolTip(self.__macTip)
        
        self.__macLe = QLineEdit()
        self.__macLe.setPlaceholderText("����10λMAC")
        self.__macLe.setToolTip(self.__macTip)
        self.__macLe.setValidator(QRegExpValidator(QRegExp(CUSTOM_MAC_RE)))
        self.__macLe.setFixedSize(80, 20)
        self.__macLe.textChanged.connect(self.__macLeEditUpper)
        
        self.__macScriptPartLb = QLabel('01')
        
        self.__runBt = QPushButton('����')
        #�и����⣬��ô����ֻ�н��㴦�ڰ�ťʱ��ݼ�����Ч
#         self.__runBt.setShortcut("Return") #Returnλ��ĸ���Ļس�����EnterΪ�������Ļس���
        self.__progressLb = QLabel()
        
        self.__progressPb = QProgressBar()
        
        self.__progressTb = QTextBrowser()
        
        self.__fileLe = QLineEdit()
        self.__fileLe.setEnabled(False)
        
        self.__openFileBt = QPushButton("���ļ�")
        self.__openFileBt.clicked.connect(self.__openFile)
        
        self.__openPathBt = QPushButton("���ļ���")
        self.__openPathBt.clicked.connect(self.__openPath)
        
        self.__helpLb = QLabel("������cwx457048")
        
    def __createLayout(self):
        bomcodeHb = QHBoxLayout()
        bomcodeHb.addWidget(self.__bomcodeLb)
        bomcodeHb.addWidget(self.__bomcodeLe)
        bomcodeHb.addStretch(1)
        
        barcodeHb = QHBoxLayout()
        barcodeHb.addWidget(self.__barcodeLb)
        barcodeHb.addWidget(self.__barcodeLe)
        barcodeHb.addWidget(self.__barcodeScriptPartLb)
        barcodeHb.addStretch(1)
        
        macHb = QHBoxLayout()
        macHb.addWidget(self.__macLb)
        macHb.addWidget(self.__macLe)
        macHb.addWidget(self.__macScriptPartLb)
        macHb.addStretch(1)
        
        inputVb = QVBoxLayout()
        inputVb.addLayout(bomcodeHb)
        inputVb.addLayout(barcodeHb)
        inputVb.addLayout(macHb)
        inputVb.addStretch(1)
        
        inputHb = QHBoxLayout()
        inputHb.addLayout(inputVb)
        inputHb.addWidget(self.__runBt)
#         inputHb.addStretch(1)
        
        inputGb = QGroupBox("����")
        inputGb.setLayout(inputHb)

        progressVb = QVBoxLayout()
        progressVb.addWidget(self.__progressLb)
        progressVb.addWidget(self.__progressPb)
        progressVb.addWidget(self.__progressTb)
        
        progressGb = QGroupBox("����")
        progressGb.setLayout(progressVb)
                    
        resultHb = QHBoxLayout()
        resultHb.addWidget(self.__fileLe)
        resultHb.addWidget(self.__openFileBt)
        resultHb.addWidget(self.__openPathBt)
        
        resultGb = QGroupBox("���")
        resultGb.setLayout(resultHb)
        
        helpHb = QHBoxLayout()
        helpHb.addStretch(1)
        helpHb.addWidget(self.__helpLb)
        
        mainVb = QVBoxLayout()
        mainVb.addWidget(inputGb)
        mainVb.addWidget(progressGb)
        mainVb.addWidget(resultGb)
        mainVb.addLayout(helpHb)
        mainVb.addStretch(1)
        
        self.setLayout(mainVb)
        
    def __openFile(self):
        if self.__fileLe.text():
#             global excelFileName
            os.startfile(self.__filepath + "\\" + excelFileName)
    
    def __openPath(self):
        os.startfile(self.__filepath)
        
    def __bomcodeLeEditUpper(self):
        self.__bomcodeLe.setText(self.__bomcodeLe.text().upper())
     
    def __barcodeLeEditUpper(self):
        self.__barcodeLe.setText(self.__barcodeLe.text().upper())
    
    def __macLeEditUpper(self):
        self.__macLe.setText(self.__macLe.text().upper())
          
    def __moveCenter(self):
        #QDesktopWidget���ṩ�����洰�ڵ���Ϣ��������Ļ�ߴ�
        qr = self.frameGeometry() #��������ڵ�һ�������ض�����ͼ�Σ��������ڵĿ��
        cp = QDesktopWidget().availableGeometry().center() #����������ʾ���ľ���ֵ�����������ֵ�У������Ļ���ĵ�
        qr.moveCenter(cp) #�������ƶ�����Ļ�м�
        self.move(qr.topLeft()) #�ƶ���Ӧ�ô��ڵ����Ϸ��ĵ㣬��qr���ε����Ϸ��ĵ㣬�Ӷ�������ʾ

class EmittingStream(QObject):  
        textWritten = pyqtSignal(str)  #����һ������str���ź�
        def write(self, text):
            self.textWritten.emit(str(text))

class GetBomThread(QThread):
    def __init__(self):
        super(GetBomThread, self).__init__()
        
    def run(self):
        global runPerNowStep
        logging.info("info-aaaaa")
        logging.warning("warniing-aaaaa")
        logging.error("error-aaaaa")
#         print("ERROR")
        runPerNowStep = 1
        for i in range(10):
            print(i)
            sleep(1)
        logging.info("info-bbbbb")
        logging.warning("warniing-bbbbb")
        logging.error("error-bbbbb")
#         print("ERROR")
        runPerNowStep = 2
        for i in range(10):
            print(i)
            sleep(1)
            
        global wb
        wb = openpyxl.Workbook()
        global excelFileName
        wb.save(excelFileName)
        runPerNowStep = 3 
       
if __name__ == '__main__':
    PDMGUIApp = QApplication(sys.argv)
    PDMGUIObj = CreateGetBomGui()
    sys.exit(PDMGUIApp.exec_())