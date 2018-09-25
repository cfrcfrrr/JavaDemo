# -*- coding: utf-8 -*-
from logging import Handler
import sys, re, logging, openpyxl, time, os, random
from time import sleep
import traceback

from PyQt5 import Qt
from PyQt5.QtCore import QRegExp, QThread, QTimer, QBasicTimer, pyqtSignal, QObject, \
    QDateTime
from PyQt5.QtGui import QRegExpValidator, QColor, QIcon, QTextCursor
from PyQt5.QtWidgets import QWidget, QDesktopWidget, QApplication, QLabel, \
    QLineEdit, QGridLayout, QPushButton, QProgressBar, QTextEdit, QTextBrowser, \
    QMessageBox, QHBoxLayout, QVBoxLayout, QGroupBox, QDialog

class Window(QDialog):
    def __init__(self):
        QDialog.__init__(self)
        self.resize(400, 100)
        self.input = QLineEdit(self)
        self.input.resize(400, 100)
        
        self.show()
        
        self.click()
        
    def click(self):
        self.b = bbb()
        self.b.bSignal[str, str].connect(self.handleDisplay)
        self.b.start()
    
#         self.c = ccc()
#         self.c.cSignal.connect(self.handleDisplay)
#         self.c.start()
                
    def handleDisplay(self, msg1, msg2):
        self.input.insert(msg1)
        self.input.insert(msg2)

class bbb(QThread):
    bSignal = pyqtSignal(str, str)
    def run(self):
#         while True:
        self.e = eee()
        self.e.logSignal[str, str].connect(self.blog)
        self.e.getBom()
        time.sleep(1)
        
    def blog(self, msg1, msg2):
        self.bSignal.emit(msg1, msg2)

class eee(QThread):
    logSignal = pyqtSignal(str, str)
    
    def __init__(self):
        super(eee, self).__init__()
    
#     def run(self):
#         print("run")
        
    def getBom(self):
        print("XXXX")
        self.log("eee", "eee2")
#         self.log("eee1")
#         sleep(1)
#         self.log("eee2")
#         sleep(1)
#         self.log("eee3")
#         sleep(2)
#         self.log("eee4")
#         sleep(2)
#         self.log("eee5")
        
    def log(self, msg1, msg2):
        self.logSignal.emit(msg1, msg2)

# class ccc(QThread):
#     cSignal = pyqtSignal(str)
#     def run(self):
#         while True:
#             self.cSignal.emit("ccc\n")
#             time.sleep(1)
            
if __name__ == '__main__':
    app = QApplication(sys.argv)
    
    w = Window() 
    
    app.exec_()