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
#QMainWindow类提供应用主窗口，默认创建一个拥有状态栏、工具栏和菜单栏的经典应用串口骨架
# class Example(QMainWindow):
    def __init__(self):
        super().__init__()
        
        self.initUI()
    
    def initUI(self):
        self.resize(500, 500*0.618) #设置窗口大小，宽度、高度
#         self.move(300, 300) #设置窗口的位置，X轴位置、Y轴位置
#         self.setGeometry(300, 300, 300, 220) #同时设置窗口的位置和大小，相当于move+resize，X轴位置、Y轴位置、宽度、高度
#         self.move(self.frameGeometry().moveCenter(QDesktopWidget().availableGeometry().center()))
        self.center()
        self.setWindowTitle('Simple') #设置标题
        self.setWindowIcon(QIcon('PDM.jpg')) #设置窗口图标
        QToolTip.setFont(QFont('SansSerif', 10)) #设置提示框的字体
        self.setToolTip('This is a <b>QWidget</b> widget') #设置窗口的提示框
        
#         self.statusBar().showMessage('Ready') #显示状态栏，需要设置父类为QMainWindow
#         
#         menubar = self.menuBar() #创建菜单栏，需要设置父类为QMainWindow
#         fileMenu = menubar.addMenu('&File') #创建file菜单
#         #QAction是一个用于菜单栏、工具栏或自定义快捷键的抽象动作行为
#         exitAction = QAction(QIcon('exit.jpg'), '&Exit', self) #设置图标、显示文本的标签
#         exitAction.setShortcut('Ctrl+W') #设置快捷键
#         exitAction.setStatusTip('Exit application') #鼠标悬浮的状态提示
#         exitAction.triggered.connect(qApp.quit) #信号-槽
#         fileMenu.addAction(exitAction) #file菜单中添加exitAction标签
#         
#         self.toolbar = self.addToolBar('mytoolbar') #创建的工具栏，需要设置父类为QMainWindow
#         self.toolbar.addAction(exitAction) #插入一个动作对象
#         
#         #绝对定位
#         lbl = QLabel('Zetcode', self) #标签
#         lbl.move(100, 260)
        
        #箱布局
#         okButton = QPushButton("OK")
#         cancelButton = QPushButton("Cancel")
        
#         hbox = QHBoxLayout() #创建水平箱布局
#         hbox.addStretch(1) #增加一个拉伸因子
#         hbox.addWidget(okButton)
#         hbox.addWidget(cancelButton)
#         
#         vbox = QVBoxLayout() #创建垂直箱布局
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
#         #网格布局1，需要设置父类为QWidget
#         grid = QGridLayout()
#         grid.setSpacing(0)
#         self.setLayout(grid) #如果前面已经调用了setLayout，则先调用的先生效
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
#         #网格布局2，需要设置父类为QWidget
#         title = QLabel('Title')
#         author = QLabel('Author')
#         review = QLabel('Review')
#
#        #单行输入框
#         titleEdit = QLineEdit() #单行编辑框
#         
#         authorEdit = QLineEdit()
#         reviewEdit = QTextEdit() #多行编辑框
#  
#         grid = QGridLayout()
#         grid.setSpacing(10) #设置组件间距
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
#         #信号-槽
#         lcd = QLCDNumber(self) #LED显示框
#         sld = QSlider(Qt.Horizontal, self) #滑块条
#  
#         vbox = QVBoxLayout()
#         vbox.addWidget(lcd)
#         vbox.addWidget(sld)
#  
#         self.setLayout(vbox)
#         sld.valueChanged.connect(lcd.display) #信号-槽，将滑块条的valueChanged信号和LED显示的display绑定在一起
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
#         cb = QCheckBox('Show title', self) #勾选框
#         cb.move(20, 20)
#         cb.toggle() #设置默认勾选
#         cb.stateChanged.connect(self.changeTitle)
# 
#         self.col = QColor(0, 0, 0)
#         redb = QPushButton('Red', self)
#         redb.setCheckable(True) #设置按钮可被选中
#         redb.move(10, 10)
#         redb.clicked[bool].connect(self.setColor) #通过clicked信号操作布尔值
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
#         self.label.setPixmap(QPixmap('mute.png')) #不知道为什么图片没显示
#         self.label.setGeometry(160, 40, 80, 30)
# 
        self.pbar = QProgressBar(self) #创建横向进度条
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
         
        self.timer = QBasicTimer() #用定时器对象激活进度条
        self.step = 0
#             
#         cal = QCalendarWidget(self) #创建日历组件
#         cal.setGridVisible(True)
#         cal.move(20, 20)
#         cal.clicked[QDate].connect(self.showDate)
#         
#         self.lb1 = QLabel(self)
#         date = cal.selectedDate()
#         self.lb1.setText(date.toString())
#         self.lb1.move(130, 260)
#         
#        #不知道为什么显示像素图显示不了
#         hbox = QHBoxLayout(self)
#         pixmap = QPixmap("redrock.png")
#         
#         lb2 = QLabel(self)
#         lb2.setPixmap(pixmap)
#         
#         hbox.addWidget(lb2)
#         self.setLayout(hbox)
# 
#         self.lb3 = QLabel(self) #不知道为什么，这个label定义的时候必须要加上self.
#         self.lb3.move(60, 40)
#         qle = QLineEdit(self) #单行编辑框
#         qle.move(60, 100)
#         qle.textChanged[str].connect(self.onChanged)
#         
#         hbox = QHBoxLayout(self)
#         topleft = QFrame(self) #框架组件
#         topleft.setFrameShape(QFrame.StyledPanel)
#         topright = QFrame(self)
#         topright.setFrameShape(QFrame.StyledPanel)
#         bottom = QFrame(self)
#         bottom.setFrameShape(QFrame.StyledPanel)
#         
#         splitter1 = QSplitter(Qt.Horizontal) #分割框组件
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
        #LineEdit详细
#         le1 = QLineEdit(self)
#         le1.setPlaceholderText("Normal+Left") #设置占位文字，也就是未输入时的提示文字。当输入文字后消失。
#         le1.setEchoMode(QLineEdit.Normal) #设置显示模式，Mormal为默认模式，输入什么就显示什么；Password密码模式，
#         #显示为小黑点；PasswordEchoOnEdit，表示编辑时显示内容，编辑完成后显示小黑点；NoEcho，表示看不到输入（实际是能输入的）
#         le1.setAlignment(Qt.AlignLeft) #设置文本对齐方式，AlignLeft为左对齐, AlignCenter为居中，AlignRight为右对齐。包括提示内容位置也会变化。
#         le1.setReadOnly(False) #设置是否只读，False为非只读，可编辑，默认值，True为只读
#         le1.setValidator(QIntValidator()) #对输入进行限制，QIntValidator设置只能输入整数[-999999999, 999999999]，还可以带参数限定数值范围，如QIntValidator(100, 900);
#         #QDoubleValidator，额，没看懂，http://www.cnblogs.com/hellovenus/p/5183593.html
#         #QRegExpValidator，正则表达式限制，如QRegExpValidator(QRegExp("[0-9A-Fa-f][02468ACEace][0-9A-Fa-f]{8}"))
#         le1.setInputMask("") #限制输入格式，""表示没有限制；其余如“+99 99 99 99 99;_”、“0000-00-00”、“00000000”、“>AAAAA-AAAAA-AAAAA-AAAAA-AAAAA;#”等，
#         #其中A表示字母A-Za-z；N表示A-Za-z0-9;X表示任意字符；9表示0-9；D表示1-9；#表示数字或+-符号；H表示A-Fa-f0-9；B表示0-1；以上对应的小写字母（9是对应0），允许但不是必须的，也就是可以留空
#         #>表示大写字母，输入小写字母会自动转换成大写；<表示小写字母，输入大写会自动转换成小写；!表示关闭大小写
#         #\用于处理上面的特殊字符，如“\\0\\xHHHH;0”表示0x接4位16进制数，其中“;0”表示先用0填充
#         le1.setMaxLength(5) #设置可以输入的最多字符数
#         le1.setDragEnabled(False) #设置可拖放，没看出什么差别
#         le1.selectAll() #全选
#         le1.setFocus() #获取焦点
#         #setText()或insert()设置文本、text()获取文本、displayText()获得显示文本、setSelection()、selectAll()选中文本，选中文本可通过cut()、copy()、paste()
#         #setText()造成的文本变更时会发出textChanged()信号，其他文本变更会发出textEdit()信号，鼠标光标改变时会发出cursorPositionChanged()信号，按返回值键回车键时，会发出returnPressed()信号
#         le1.editingFinished.connect(self.changeLe5Text) #编辑结束，失去焦点或按返回或按回车键时，会发送editingFinished信号
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
# #         self.le6.setInputMask(">HH:HH:HH:HH:HH:\\01;0") #很奇怪，必须设置默认值，要不然setValidator的就没生效，会被setInputMask的规则覆盖
#         self.le6.textEdited.connect(self.lowerChangeUpper) #小写转大写
#         self.le6.setFixedSize(110, 20) #resize
#         self.le6.move(10, 210)
#         
        self.show()
    
    #输入时小写转大写
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
        
    #重写timeEvent函数，每个QObject类和它的子类都有timerEvent()事件处理函数用于处理定时事件
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
            self.timer.start(100, self) #开启定时器事件，参数1是定时事件，参数2是接收定时器事件的对象
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
            self.col.setRed(val) #更改RGB值中红色部分的颜色值
        elif source.text() == "Green":
            self.col.setGreen(val)
        else:
            self.col.setBlue(val)
        
        self.square.setStyleSheet("QFrame { background-color: %s }" % self.col.name()) #通过样式表修改背景颜色
        
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
        font, ok = QFontDialog.getFont() #弹出字体选择框，getFont方法返回字体名字和布尔值
        if ok:
            self.lb1.setFont(font)
        
    def showDialog2(self):
        col = QColorDialog.getColor() #弹出颜色选择框，getColor方法返回选择的颜色和布尔值

        if col.isValid():
            self.frm.setStyleSheet("QWidget { background-color: %s }" % col.name())
    
    def showDialog(self):
        text, ok = QInputDialog.getText(self, 'Input Dialog', 'Enter your name:') #显示对话框
         
        if ok:
            self.le.setText(str(text))
         
#     def mousePressEvent(self, event): #在窗口上点一下鼠标，closeApp信号被发射，应用中断
#         self.c.closeApp.emit()
#      
    #重定向关闭事件   
    def closeEvent(self, event):
        reply = QMessageBox.question(self, 'Message', 'Are you sure to quit?', QMessageBox.Yes | QMessageBox.No, QMessageBox.No) #第二个参数是标题栏内容，第三个参数是对话框文本，第四个是按钮集合，第五个参数是默认选中按钮
          
        if reply == QMessageBox.Yes:
            event.accept()
        else:
            event.ignore()
              
    def center(self):
        #QDesktopWidget类提供了桌面窗口的信息，包含屏幕尺寸
        qr = self.frameGeometry() #获得主窗口的一个矩形特定几何图形，包含窗口的框架
        cp = QDesktopWidget().availableGeometry().center() #算出相对于显示器的绝对值，从这个绝对值中，获得屏幕中心点
        qr.moveCenter(cp) #将矩形移动到屏幕中间
        self.move(qr.topLeft()) #移动了应用窗口的左上方的点，到qr矩形的左上方的点，从而居中显示
#  
#     def buttonClicked(self):
#         sender = self.sender() #sender方法判断发送信号的信号源
#         self.statusBar().showMessage(sender.text() + ' was pressed')
    
class Communicate(QObject):
    closeApp = pyqtSignal() #使用pyqtSignal()创建新信号，并且成为外部类Communicate类的属性
    
if __name__ == '__main__':
    app = QApplication(sys.argv) #所有的PyQt5应用必须创建一个Application对象。sys.argv表示命令行参数，可以直接传个空列表，app = QApplication([[)
    
#     w = QWidget() #QWidget组件是所有用户界面类的基础类，没有设置父类的QWidget表示窗口
    w = Example()
    
#     ebtn = QPushButton('ExampleButton', w) #第一个参数是按钮的显示文本，第二个参数是父组件
#     ebtn.setToolTip('This is a <b>QPushButton</b> widget')
#     ebtn.resize(ebtn.sizeHint()) #给按钮设置推荐的大小
#     ebtn.move(100, 80) #设置相较于父对象的位置
    
#     qbtn = QPushButton('Quit', w)
    #PyQt5事件处理系统由信号&槽位机制建立，槽可以是Qt内置的槽或python方法调用
    #QCoreApplication类包含主事件循环，它处理和转发所有事件，instance()方法返回一个实例化对象
#     qbtn.clicked.connect(QCoreApplication.instance().quit) #点击按钮，则信号clicked被发送，QCoreApplication.instance().quit被调用
#     
#     qbtn.resize(qbtn.sizeHint())
#     qbtn.move(100,180)
    
#     w.show() #显示窗口
    
    sys.exit(app.exec_()) #app.exec_()表示应用进入主循环，接收来自窗口触发的事件，并且转发到widget应用上处理。调用exit()或主widget组件被销毁，则主循环退出。sys.exit()确保一个不留垃圾的退出。
    