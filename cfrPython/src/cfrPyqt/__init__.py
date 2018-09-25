#-coding:gbk-*-
import sys, re
from PyQt5.QtWidgets import QApplication, QWidget, QToolTip, QPushButton, QMessageBox,\
    QDesktopWidget, QMainWindow, qApp, QAction, QLabel, QHBoxLayout, QVBoxLayout,\
     QGridLayout, QLineEdit, QTextEdit, QLCDNumber, QSlider, QInputDialog,\
    QFrame, QColorDialog, QSizePolicy, QFileDialog, QCheckBox, QProgressBar,\
    QCalendarWidget, QSplitter, QComboBox, QGroupBox
from PyQt5.QtGui import QIcon, QFont, QColor, QPixmap, QIntValidator, QValidator,\
    QRegExpValidator, QDoubleValidator
from PyQt5.QtCore import QCoreApplication, Qt, QObject, pyqtSignal, QBasicTimer, QDate,\
    QRegExp
from PyQt5.Qt import QFontDialog

class Example(QWidget):
#QMainWindow���ṩӦ�������ڣ�Ĭ�ϴ���һ��ӵ��״̬�����������Ͳ˵����ľ���Ӧ�ô��ڹǼ�
# class Example(QMainWindow):
    def __init__(self):
        super().__init__()
        
        self.initUI()
    
    def initUI(self):
        self.resize(500, 500*0.618) #���ô��ڴ�С����ȡ��߶�
#         self.move(300, 300) #���ô��ڵ�λ�ã�X��λ�á�Y��λ��
#         self.setGeometry(300, 300, 300, 220) #ͬʱ���ô��ڵ�λ�úʹ�С���൱��move+resize��X��λ�á�Y��λ�á���ȡ��߶�
#         self.move(self.frameGeometry().moveCenter(QDesktopWidget().availableGeometry().center()))
        self.center()
        self.setWindowTitle('Simple') #���ñ���
        self.setWindowIcon(QIcon('PDM.jpg')) #���ô���ͼ��
        QToolTip.setFont(QFont('SansSerif', 10)) #������ʾ�������
        self.setToolTip('This is a <b>QWidget</b> widget') #���ô��ڵ���ʾ��
        
#         self.statusBar().showMessage('Ready') #��ʾ״̬������Ҫ���ø���ΪQMainWindow
#         
#         menubar = self.menuBar() #�����˵�������Ҫ���ø���ΪQMainWindow
#         fileMenu = menubar.addMenu('&File') #����file�˵�
#         #QAction��һ�����ڲ˵��������������Զ����ݼ��ĳ�������Ϊ
#         exitAction = QAction(QIcon('exit.jpg'), '&Exit', self) #����ͼ�ꡢ��ʾ�ı��ı�ǩ
#         exitAction.setShortcut('Ctrl+W') #���ÿ�ݼ�
#         exitAction.setStatusTip('Exit application') #���������״̬��ʾ
#         exitAction.triggered.connect(qApp.quit) #�ź�-��
#         fileMenu.addAction(exitAction) #file�˵������exitAction��ǩ
#         
#         self.toolbar = self.addToolBar('mytoolbar') #�����Ĺ���������Ҫ���ø���ΪQMainWindow
#         self.toolbar.addAction(exitAction) #����һ����������
#         
#         #���Զ�λ
#         lbl = QLabel('Zetcode', self) #��ǩ
#         lbl.move(100, 260)
        
        #�䲼��
#         okButton = QPushButton("OK")
#         cancelButton = QPushButton("Cancel")
        
#         hbox = QHBoxLayout() #����ˮƽ�䲼��
#         hbox.addStretch(1) #����һ����������
#         hbox.addWidget(okButton)
#         hbox.addWidget(cancelButton)
#         
#         vbox = QVBoxLayout() #������ֱ�䲼��
#         vbox.addStretch(1)
#         vbox.addLayout(hbox)
#         
#         self.setLayout(vbox)
#         hbox = QHBoxLayout()
#         hbox.addStretch(1)
#         hbox.addWidget(okButton)
#         hbox.addWidget(cancelButton)
#  
#         vbox = QVBoxLayout()
#         vbox.addStretch(1)
#         vbox.addLayout(hbox)
        
#         self.setLayout(vbox)
# 
#         #���񲼾�1����Ҫ���ø���ΪQWidget
#         grid = QGridLayout()
#         grid.setSpacing(0)
#         self.setLayout(grid) #���ǰ���Ѿ�������setLayout�����ȵ��õ�����Ч
#         names = ['Cls', 'Bck', '', 'Close',
#                  '7', '8', '9', '/',
#                 '4', '5', '6', '*',
#                  '1', '2', '3', '-',
#                 '0', '.', '=', '+']
#           
#         positions = [(i,j) for i in range(5) for j in range(4)]
#           
#         for position, name in zip(positions, names):
#               
#             if name == '':
#                 continue
#             button = QPushButton(name)
#             grid.addWidget(button, *position)
#         
#         #���񲼾�2����Ҫ���ø���ΪQWidget
#         title = QLabel('Title')
#         author = QLabel('Author')
#         review = QLabel('Review')
#
#        #���������
#         titleEdit = QLineEdit() #���б༭��
#         
#         authorEdit = QLineEdit()
#         reviewEdit = QTextEdit() #���б༭��
#  
#         grid = QGridLayout()
#         grid.setSpacing(10) #����������
#   
#         grid.addWidget(title, 1, 0)
#         grid.addWidget(titleEdit, 1, 1)
#  
#         grid.addWidget(author, 2, 0)
#         grid.addWidget(authorEdit, 2, 1)
#  
#         grid.addWidget(review, 3, 0)
#         grid.addWidget(reviewEdit, 3, 1, 5, 1)
#          
#         self.setLayout(grid)
#         
#         #�ź�-��
#         lcd = QLCDNumber(self) #LED��ʾ��
#         sld = QSlider(Qt.Horizontal, self) #������
#  
#         vbox = QVBoxLayout()
#         vbox.addWidget(lcd)
#         vbox.addWidget(sld)
#  
#         self.setLayout(vbox)
#         sld.valueChanged.connect(lcd.display) #�ź�-�ۣ�����������valueChanged�źź�LED��ʾ��display����һ��
#         
#         btn1 = QPushButton("Button 1", self)
#         btn1.move(30, 50)
#         btn2 = QPushButton("Button 2", self)
#         btn2.move(150, 50)
#         btn1.clicked.connect(self.buttonClicked)
#         btn2.clicked.connect(self.buttonClicked)
        
#         self.c = Communicate()
#         self.c.closeApp.connect(self.close)
#         
#         self.btn = QPushButton('Dialog', self)
#         self.btn.move(20, 20)
#         self.btn.clicked.connect(self.showDialog)
#         
#         self.le = QLineEdit(self)
#         self.le.move(130, 22)
#         
#         col = QColor(0, 0, 0)
#         self.btn = QPushButton('Dialog', self)
#         self.btn.move(20, 20)
#         self.btn.clicked.connect(self.showDialog2)
#         
#         self.frm = QFrame(self)
#         self.frm.setStyleSheet("QWidget { background-color: %s }" 
#                                % col.name())
#         self.frm.setGeometry(130, 22, 100, 100)
#         
#         col = QColor(0, 0, 0)
#  
#         self.btn = QPushButton('Dialog', self)
#         self.btn.move(20, 20)
#  
#         self.btn.clicked.connect(self.showDialog2)
#  
#         self.frm = QFrame(self)
#         self.frm.setStyleSheet("QWidget { background-color: %s }"
#             % col.name())
#         self.frm.setGeometry(130, 22, 100, 100) 
#         
#         vbox = QVBoxLayout()
#         
#         btn = QPushButton('Dialog', self)
#         btn.setSizePolicy(QSizePolicy.Fixed, QSizePolicy.Fixed)
#         btn.move(20, 20)
#         
#         vbox.addWidget(btn)
#         
#         btn.clicked.connect(self.showDialog3)
#         
#         self.lb1 = QLabel('Knowledge only matters', self)
#         self.lb1.move(130, 20)
#         
#         vbox.addWidget(self.lb1)
#         self.setLayout(vbox)
#         
#         
#         self.textEdit = QTextEdit()
#         self.setCentralWidget(self.textEdit)
#         self.statusBar()
#         
#         openFile = QAction(QIcon('open.png'), 'Open', self)
#         openFile.setShortcut('Ctrl+O')
#         openFile.setStatusTip('Open new File')
#         openFile.triggered.connect(self.showDialog4)
#         
#         menubar = self.menuBar()
#         fileMenu = menubar.addMenu('&File')
#         fileMenu.addAction(openFile)
#         
#         cb = QCheckBox('Show title', self) #��ѡ��
#         cb.move(20, 20)
#         cb.toggle() #����Ĭ�Ϲ�ѡ
#         cb.stateChanged.connect(self.changeTitle)
# 
#         self.col = QColor(0, 0, 0)
#         redb = QPushButton('Red', self)
#         redb.setCheckable(True) #���ð�ť�ɱ�ѡ��
#         redb.move(10, 10)
#         redb.clicked[bool].connect(self.setColor) #ͨ��clicked�źŲ�������ֵ
#         
#         greb = QPushButton('Green', self)
#         greb.setCheckable(True)
#         greb.move(10, 60)
#         greb.clicked[bool].connect(self.setColor)
#         
#         blueb = QPushButton('Blue', self)
#         blueb.setCheckable(True)
#         blueb.move(10, 110)
#         blueb.clicked[bool].connect(self.setColor)
#         
#         self.square = QFrame(self)
#         self.square.setGeometry(150, 20, 100, 100)
#         self.square.setStyleSheet("QWidget { background-color: %s }" % self.col.name())
#     
#         sld = QSlider(Qt.Horizontal, self)
#         sld.setFocusPolicy(Qt.NoFocus)
#         sld.setGeometry(30, 40, 100, 30)
#         sld.valueChanged[int].connect(self.changeValue)
#         
#         self.label = QLabel(self)
#         self.label.setPixmap(QPixmap('mute.png')) #��֪��ΪʲôͼƬû��ʾ
#         self.label.setGeometry(160, 40, 80, 30)
# 
        self.pbar = QProgressBar(self) #�������������
        self.pbar.setValue(40)
        style = """
                QProgressBar {
                    border: 2px solid grey;
                    border-radius: 5px;
                    text-align: center;
                }
                QProgressBar::chunk {
                    background-color: #%s;
                    width: 20px;
                }""" % '37DA7E'
        self.pbar.setStyleSheet(style)
        
        self.pbar.setGeometry(30, 40, 200, 25)
         
        self.btn = QPushButton('Start', self)
        self.btn.move(40, 80)
        self.btn.clicked.connect(self.doAction)
         
        self.timer = QBasicTimer() #�ö�ʱ�����󼤻������
        self.step = 0
#             
#         cal = QCalendarWidget(self) #�����������
#         cal.setGridVisible(True)
#         cal.move(20, 20)
#         cal.clicked[QDate].connect(self.showDate)
#         
#         self.lb1 = QLabel(self)
#         date = cal.selectedDate()
#         self.lb1.setText(date.toString())
#         self.lb1.move(130, 260)
#         
#        #��֪��Ϊʲô��ʾ����ͼ��ʾ����
#         hbox = QHBoxLayout(self)
#         pixmap = QPixmap("redrock.png")
#         
#         lb2 = QLabel(self)
#         lb2.setPixmap(pixmap)
#         
#         hbox.addWidget(lb2)
#         self.setLayout(hbox)
# 
#         self.lb3 = QLabel(self) #��֪��Ϊʲô�����label�����ʱ�����Ҫ����self.
#         self.lb3.move(60, 40)
#         qle = QLineEdit(self) #���б༭��
#         qle.move(60, 100)
#         qle.textChanged[str].connect(self.onChanged)
#         
#         hbox = QHBoxLayout(self)
#         topleft = QFrame(self) #������
#         topleft.setFrameShape(QFrame.StyledPanel)
#         topright = QFrame(self)
#         topright.setFrameShape(QFrame.StyledPanel)
#         bottom = QFrame(self)
#         bottom.setFrameShape(QFrame.StyledPanel)
#         
#         splitter1 = QSplitter(Qt.Horizontal) #�ָ�����
#         splitter1.addWidget(topleft)
#         splitter1.addWidget(topright)
#         splitter2 = QSplitter(Qt.Vertical)
#         splitter2.addWidget(splitter1)
#         splitter2.addWidget(bottom)
#         
#         hbox.addWidget(splitter2)
#         self.setLayout(hbox)
# 
#         self.lb1 = QLabel("Ubuntu", self)
#         combo = QComboBox(self)
#         combo.addItem("Ubuntu")
#         combo.addItem("Mandriva")
#         combo.addItem("Fedora")
#         combo.addItem("Arch")
#         combo.addItem("Gentoo")
#         
#         combo.move(50, 50)
#         self.lb1.move(50, 150)
#         
#         combo.activated[str].connect(self.onActivated)
#         
#         
#         gb = QGroupBox(self)
#         gb.move(30, 30)
#
        #LineEdit��ϸ
#         le1 = QLineEdit(self)
#         le1.setPlaceholderText("Normal+Left") #����ռλ���֣�Ҳ����δ����ʱ����ʾ���֡����������ֺ���ʧ��
#         le1.setEchoMode(QLineEdit.Normal) #������ʾģʽ��MormalΪĬ��ģʽ������ʲô����ʾʲô��Password����ģʽ��
#         #��ʾΪС�ڵ㣻PasswordEchoOnEdit����ʾ�༭ʱ��ʾ���ݣ��༭��ɺ���ʾС�ڵ㣻NoEcho����ʾ���������루ʵ����������ģ�
#         le1.setAlignment(Qt.AlignLeft) #�����ı����뷽ʽ��AlignLeftΪ�����, AlignCenterΪ���У�AlignRightΪ�Ҷ��롣������ʾ����λ��Ҳ��仯��
#         le1.setReadOnly(False) #�����Ƿ�ֻ����FalseΪ��ֻ�����ɱ༭��Ĭ��ֵ��TrueΪֻ��
#         le1.setValidator(QIntValidator()) #������������ƣ�QIntValidator����ֻ����������[-999999999, 999999999]�������Դ������޶���ֵ��Χ����QIntValidator(100, 900);
#         #QDoubleValidator���û������http://www.cnblogs.com/hellovenus/p/5183593.html
#         #QRegExpValidator��������ʽ���ƣ���QRegExpValidator(QRegExp("[0-9A-Fa-f][02468ACEace][0-9A-Fa-f]{8}"))
#         le1.setInputMask("") #���������ʽ��""��ʾû�����ƣ������硰+99 99 99 99 99;_������0000-00-00������00000000������>AAAAA-AAAAA-AAAAA-AAAAA-AAAAA;#���ȣ�
#         #����A��ʾ��ĸA-Za-z��N��ʾA-Za-z0-9;X��ʾ�����ַ���9��ʾ0-9��D��ʾ1-9��#��ʾ���ֻ�+-���ţ�H��ʾA-Fa-f0-9��B��ʾ0-1�����϶�Ӧ��Сд��ĸ��9�Ƕ�Ӧ0�����������Ǳ���ģ�Ҳ���ǿ�������
#         #>��ʾ��д��ĸ������Сд��ĸ���Զ�ת���ɴ�д��<��ʾСд��ĸ�������д���Զ�ת����Сд��!��ʾ�رմ�Сд
#         #\���ڴ�������������ַ����硰\\0\\xHHHH;0����ʾ0x��4λ16�����������С�;0����ʾ����0���
#         le1.setMaxLength(5) #���ÿ������������ַ���
#         le1.setDragEnabled(False) #���ÿ��Ϸţ�û����ʲô���
#         le1.selectAll() #ȫѡ
#         le1.setFocus() #��ȡ����
#         #setText()��insert()�����ı���text()��ȡ�ı���displayText()�����ʾ�ı���setSelection()��selectAll()ѡ���ı���ѡ���ı���ͨ��cut()��copy()��paste()
#         #setText()��ɵ��ı����ʱ�ᷢ��textChanged()�źţ������ı�����ᷢ��textEdit()�źţ������ı�ʱ�ᷢ��cursorPositionChanged()�źţ�������ֵ���س���ʱ���ᷢ��returnPressed()�ź�
#         le1.editingFinished.connect(self.changeLe5Text) #�༭������ʧȥ����򰴷��ػ򰴻س���ʱ���ᷢ��editingFinished�ź�
#         le1.move(10, 10)
#         
#         le2 = QLineEdit(self)
#         le2.setPlaceholderText("Password+Center")
#         le2.setEchoMode(QLineEdit.Password)
#         le2.setAlignment(Qt.AlignCenter)
# #         vld = QRegExpValidator("[0-9a-zA-Z]")
#         le2.move(10, 50)
#         
#         le3 = QLineEdit(self)
#         le3.setPlaceholderText("PasswordEchoOnEdit+Right")
#         le3.setEchoMode(QLineEdit.PasswordEchoOnEdit)
#         le3.setAlignment(Qt.AlignRight) 
#         le3.move(10, 90)
#         
#         le4 = QLineEdit(self)
#         le4.setPlaceholderText("NoEcho")
#         le4.setEchoMode(QLineEdit.NoEcho) 
#         le4.move(10, 130)
#         
#         self.le5 = QLineEdit(self)
#         self.le5.setPlaceholderText("Read only")
#         self.le5.setReadOnly(True)
#         self.le5.move(10, 170)
#         
#         self.le6 = QLineEdit(self)
# #         self.le6.setValidator(QRegExpValidator(QRegExp("[0-8][0-9]:[0-5][0-9]:[0-5][0-9]")))
# #         self.le6.setInputMask("00:00:00;0")
#         self.le6.setValidator(QRegExpValidator(QRegExp("^[0-9A-Fa-f][02468ACEace]:[0-9A-Fa-f][0-9A-Fa-f]:[0-9A-Fa-f][0-9A-Fa-f]:[0-9A-Fa-f][0-9A-Fa-f]:[0-9A-Fa-f][0-9A-Fa-f]:01$")))
# #         self.le6.setInputMask(">HH:HH:HH:HH:HH:\\01;0") #����֣���������Ĭ��ֵ��Ҫ��ȻsetValidator�ľ�û��Ч���ᱻsetInputMask�Ĺ��򸲸�
#         self.le6.textEdited.connect(self.lowerChangeUpper) #Сдת��д
#         self.le6.setFixedSize(110, 20) #resize
#         self.le6.move(10, 210)
#         
        self.show()
    
    #����ʱСдת��д
    def lowerChangeUpper(self):
        self.le6.setText(self.le6.text().upper())
        
    def changeLe5Text(self):
        self.le5.setText("LineEdit1 edit finished.")
        
    def onActivated(self, text):
        self.lb1.setText(text)
        self.lb1.adjustSize()
        
    def onChanged(self, text):
        self.lb3.setText(text)
        self.lb3.adjustSize()
        
    def showDate(self, date):
        self.lb1.setText(date.toString())
        
    #��дtimeEvent������ÿ��QObject����������඼��timerEvent()�¼����������ڴ���ʱ�¼�
    def timerEvent(self, e):
        if self.step >= 100:
            self.timer.stop()
            self.btn.setText('Finished')
            return
        
        self.step = self.step + 1
        self.pbar.setValue(self.step)
        
    def doAction(self):
        if self.timer.isActive():
            self.timer.stop()
            self.btn.setText('Start')
        else:
            self.timer.start(100, self) #������ʱ���¼�������1�Ƕ�ʱ�¼�������2�ǽ��ն�ʱ���¼��Ķ���
            self.btn.setText('Stop')
           
    def changeValue(self, value):
        if value == 0:
            self.label.setPixmap(QPixmap('mute.png'))
        elif value > 0 and value <= 30:
            self.label.setPixmap(QPixmap('min.png'))
        elif value > 30 and value < 80:
            self.label.setPixmap(QPixmap('med.png'))
        else:
            self.label.setPixmap(QPixmap('max.png'))
            
            
        
    def setColor(self, pressed):
        source = self.sender()
        
        if pressed:
            val = 255
        else:
            val = 0
        
        if source.text() == 'Red':
            self.col.setRed(val) #����RGBֵ�к�ɫ���ֵ���ɫֵ
        elif source.text() == "Green":
            self.col.setGreen(val)
        else:
            self.col.setBlue(val)
        
        self.square.setStyleSheet("QFrame { background-color: %s }" % self.col.name()) #ͨ����ʽ���޸ı�����ɫ
        
    def changeTitle(self, state):
        if state == Qt.Checked:
            self.setWindowTitle('QCheckBox')
        else:
            self.setWindowTitle('')
        
    def showDialog4(self):
        fname = QFileDialog.getOpenFileName(self, 'Open file', '/home')
        
        if fname[0]:
            f = open(fname[0], 'r')
            with f:
                data = f.read()
                self.textEdit.setText(data)
        
    def showDialog3(self):
        font, ok = QFontDialog.getFont() #��������ѡ���getFont���������������ֺͲ���ֵ
        if ok:
            self.lb1.setFont(font)
        
    def showDialog2(self):
        col = QColorDialog.getColor() #������ɫѡ���getColor��������ѡ�����ɫ�Ͳ���ֵ

        if col.isValid():
            self.frm.setStyleSheet("QWidget { background-color: %s }" % col.name())
    
    def showDialog(self):
        text, ok = QInputDialog.getText(self, 'Input Dialog', 'Enter your name:') #��ʾ�Ի���
         
        if ok:
            self.le.setText(str(text))
         
#     def mousePressEvent(self, event): #�ڴ����ϵ�һ����꣬closeApp�źű����䣬Ӧ���ж�
#         self.c.closeApp.emit()
#      
    #�ض���ر��¼�   
    def closeEvent(self, event):
        reply = QMessageBox.question(self, 'Message', 'Are you sure to quit?', QMessageBox.Yes | QMessageBox.No, QMessageBox.No) #�ڶ��������Ǳ��������ݣ������������ǶԻ����ı������ĸ��ǰ�ť���ϣ������������Ĭ��ѡ�а�ť
          
        if reply == QMessageBox.Yes:
            event.accept()
        else:
            event.ignore()
              
    def center(self):
        #QDesktopWidget���ṩ�����洰�ڵ���Ϣ��������Ļ�ߴ�
        qr = self.frameGeometry() #��������ڵ�һ�������ض�����ͼ�Σ��������ڵĿ��
        cp = QDesktopWidget().availableGeometry().center() #����������ʾ���ľ���ֵ�����������ֵ�У������Ļ���ĵ�
        qr.moveCenter(cp) #�������ƶ�����Ļ�м�
        self.move(qr.topLeft()) #�ƶ���Ӧ�ô��ڵ����Ϸ��ĵ㣬��qr���ε����Ϸ��ĵ㣬�Ӷ�������ʾ
#  
#     def buttonClicked(self):
#         sender = self.sender() #sender�����жϷ����źŵ��ź�Դ
#         self.statusBar().showMessage(sender.text() + ' was pressed')
    
class Communicate(QObject):
    closeApp = pyqtSignal() #ʹ��pyqtSignal()�������źţ����ҳ�Ϊ�ⲿ��Communicate�������
    
if __name__ == '__main__':
    app = QApplication(sys.argv) #���е�PyQt5Ӧ�ñ��봴��һ��Application����sys.argv��ʾ�����в���������ֱ�Ӵ������б�app = QApplication([[)
    
#     w = QWidget() #QWidget����������û�������Ļ����࣬û�����ø����QWidget��ʾ����
    w = Example()
    
#     ebtn = QPushButton('ExampleButton', w) #��һ�������ǰ�ť����ʾ�ı����ڶ��������Ǹ����
#     ebtn.setToolTip('This is a <b>QPushButton</b> widget')
#     ebtn.resize(ebtn.sizeHint()) #����ť�����Ƽ��Ĵ�С
#     ebtn.move(100, 80) #��������ڸ������λ��
    
#     qbtn = QPushButton('Quit', w)
    #PyQt5�¼�����ϵͳ���ź�&��λ���ƽ������ۿ�����Qt���õĲۻ�python��������
    #QCoreApplication��������¼�ѭ�����������ת�������¼���instance()��������һ��ʵ��������
#     qbtn.clicked.connect(QCoreApplication.instance().quit) #�����ť�����ź�clicked�����ͣ�QCoreApplication.instance().quit������
#     
#     qbtn.resize(qbtn.sizeHint())
#     qbtn.move(100,180)
    
#     w.show() #��ʾ����
    
    sys.exit(app.exec_()) #app.exec_()��ʾӦ�ý�����ѭ�����������Դ��ڴ������¼�������ת����widgetӦ���ϴ�������exit()����widget��������٣�����ѭ���˳���sys.exit()ȷ��һ�������������˳���
    