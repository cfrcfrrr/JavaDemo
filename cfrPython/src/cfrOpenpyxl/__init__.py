#-*-coding:gbk-*-    #设置成gbk就可以往表格中写中文
#openpyxl这个库书上和网上写的很多案例已经无效了，估计库更新比较频繁，慎用
import openpyxl, time, datetime
from openpyxl.utils.cell import get_column_letter
from openpyxl import load_workbook
from openpyxl.drawing.image import Image
from openpyxl.comments import Comment
from openpyxl.styles import PatternFill, Border, Side, Alignment, Protection, Font, NamedStyle
from openpyxl.styles import colors
from copy import copy
from openpyxl.cell import Cell
from openpyxl.styles.fills import FILL_SOLID
from openpyxl.styles.fills import Fill
everyBomColNum = 6

# class PdmBom():
#     #怎么指定传入的变量类型？
#     def __init__(self, bomStr, cnDesStr, usDesStr, numInt, locStr, typeStr):
#         self.bom = bomStr
#         self.cnDes = cnDesStr
#         self.usDes = usDesStr
#         self.num = numInt
#         self.loc = locStr
#         self.type = typeStr
#         self.sonObj = []
#         self.rowNum = 2
#         self.colNum = 1
#     
#     def depth(self):
#         #本来是存了编码，但是不知道怎么通过编码反找到对象，不知道有没有类似java反射的机制
#         self.addExcel()
#         for sonIndex in range(len(self.sonObj)):
#             if sonIndex == 0:
#                 self.sonObj[sonIndex].rowNum = self.rowNum
#             else:
#                 global deptestRowNum
#                 self.sonObj[sonIndex].rowNum = deptestRowNum + 1
#             self.sonObj[sonIndex].colNum = self.colNum + everyBomColNum
#             # 这里应该传入对象，怎么通过对象的bom值反找到对象？ 
#             self.sonObj[sonIndex].depth()
#     
#     def addExcel(self):
#         global fontObj
#         activeSheet.cell(row=self.rowNum, column=self.colNum).value = self.bom
#         activeSheet.cell(row=self.rowNum, column=self.colNum+1).value = self.cnDes
#         activeSheet.cell(row=self.rowNum, column=self.colNum+2).value = self.usDes
#         activeSheet.cell(row=self.rowNum, column=self.colNum+3).value = self.num
#         activeSheet.cell(row=self.rowNum, column=self.colNum+4).value = self.loc
#         activeSheet.cell(row=self.rowNum, column=self.colNum+5).value = self.type
#         global deptestRowNum
#         if self.rowNum > deptestRowNum:
#             deptestRowNum = self.rowNum
#         if activeSheet.cell(row=1, column=self.colNum).value == None:
#             activeSheet.cell(row=1, column=self.colNum).value = "编码"
#             activeSheet.cell(row=1, column=self.colNum+1).value = "中文描述"
#             activeSheet.cell(row=1, column=self.colNum+2).value = "英文描述"
#             activeSheet.cell(row=1, column=self.colNum+3).value = "数量"
#             activeSheet.cell(row=1, column=self.colNum+4).value = "位号"
#             activeSheet.cell(row=1, column=self.colNum+5).value = "属性"

if __name__ == '__main__':
    #模拟数据
#     topFatherBomObj = PdmBom('02301246', '3U 整机', '3U Whole Machine', 1, '', 'PH')
#     bom02351379 = PdmBom('02351379', '电源', 'Power Supply', 2, 'PSU0, PSU1', 'AI')
#     bom03021234 = PdmBom('03021234', '电源子模块', 'Power Supply Son', 1, '', 'AI')
#     bom03057769 = PdmBom('03057769', '3U 主控板', '3U Board', 2, '', 'AI')
#     bom03037653 = PdmBom('03037653', '3U 主控板2', '3U Board2', 1, '', 'PH')
#     bom02301UWW = PdmBom('02301UWW', '内存', 'memory', 16, '', 'WS')
#     bom03021235 = PdmBom('03021235', '电源子模块', 'Power Supply Son', 1, '', 'PH')
#     bom03024BGY = PdmBom('03024BGY', '3U 主控板3', '3U Board3', 1, '', 'AI')
#     bom02301BUW = PdmBom('02301BUW', '系统盘', 'System Disk', 2, 'FANH-J11', 'AI')
#     topFatherBomObj.sonObj.append(bom02351379)
#     topFatherBomObj.sonObj.append(bom03057769)
#     bom02351379.sonObj.append(bom03021234)
#     bom03057769.sonObj.append(bom03037653)
#     bom03057769.sonObj.append(bom02301UWW)
#     bom02301UWW.sonObj.append(bom03021235)
#     bom03037653.sonObj.append(bom03024BGY)
#     bom03037653.sonObj.append(bom02301BUW)

#     global deptestRowNum
#     deptestRowNum = 0
    
    #创建空WorkBook对象
    wb = openpyxl.Workbook()
    #获取当前活动表
    ws = wb.get_active_sheet()
    #修改表名
    ws.title = 'test1'
    
    #创建新表
    test2ws = wb.create_sheet(title="test2")
    test3ws = wb.create_sheet("test3")
    
    #加载表
    loadWorkBook = load_workbook(filename = 'tmp.xlsx')
    loadWorkSheet = loadWorkBook['Sheet1']
    
    #设置自动识别数据类型，默认为True
    wb.guess_types = True
    ws['A1'] = '3.14%'
    print(ws['A1'].value) #0.031400000000000004
    print(ws['A1'].number_format) #0%
    wb.guess_types = False
    ws['A2'] = '3.14%'
    print(ws['A2'].value) #3.14%
    print(ws['A2'].number_format) #General
    
    #使用公式
    ws['A3'] = "=SUM(1, 1)"
    print(ws['A3'].value) #这样打印出来的值仍是公式“=SUM(1, 1)”， 实际上表格中显示内容为2
    
    #合并、取消合并单元格
    ws['B1'] = 'bbb111'
    ws['B2'] = 'bbb222'
    ws['C1'] = 'ccc111'
    ws['C2'] = 'ccc222'
    ws.merge_cells('B1:C2')
    print(ws['B1'].value) #bbb111
    print(ws['B2'].value) #None
    print(ws['C1'].value) #None
    print(ws['C2'].value) #None
    ws.unmerge_cells('B1:C2')
    print(ws['B1'].value) #bbb111
    print(ws['B2'].value) #None
    print(ws['C1'].value) #None
    print(ws['C2'].value) #None
    
    ws['B1'] = 'bbb111'
    ws['B2'] = 'bbb222'
    ws['C1'] = 'ccc111'
    ws['C2'] = 'ccc222'
    ws.merge_cells(start_row=1, start_column=2, end_row=2, end_column = 3)
    ws.unmerge_cells(start_row=1, start_column=2, end_row=2, end_column = 3)
    
    #插入图片
#     tmpImg = Image('tmpImg.png')
#     ws.add_image(tmpImg, 'D1')

    #折叠列，我都不知道excel还有这功能
#     ws.column_dimensions.group('A', 'C', hidden=True)
#     ws.column_dimensions.group('A', 'C', hidden=False)，这样展开不了
    
    #添加注释，但是表格里面没看出来，不知道什么情况
#     commentObj = ws['B1'].comment
#     commentObj = Comment('This is the comment text', 'Comment Author')
#     print(commentObj.text) #This is the comment text
#     print(commentObj.author) #Comment Author
    #这种方式可以
    commentObj2 = Comment('Text3', 'Author2')
    ws['B1'].comment = commentObj2
    
    #样式
    #默认值
    #字体
#   font = Font(name='Calibri', #字体
#                  size=11,    #字号
#                  bold=False,    #加粗
#                  italic=False,    #倾斜
#                  vertAlign=None,    #字体的垂直对齐方式，可设置为'baseline', 'superscript', 'subscript'，分别为居中、靠上和靠下
#                  underline='none',    #下划线
#                  strike=False,    #删除线
#                  color='FF000000')    #字体颜色
    #渐变？
#  fill = PatternFill(fill_type=None,
#                  start_color='FFFFFFFF',
#                  end_color='FF000000')
    #边框
#  border = Border(left=Side(border_style=None,
#                            color='FF000000'),
#                  right=Side(border_style=None,
#                             color='FF000000'),
#                  top=Side(border_style=None,
#                           color='FF000000'),
#                  bottom=Side(border_style=None,
#                              color='FF000000'),
#                  diagonal=Side(border_style=None,
#                                color='FF000000'),
#                  diagonal_direction=0,
#                  outline=Side(border_style=None,
#                               color='FF000000'),
#                  vertical=Side(border_style=None,
#                                color='FF000000'),
#                  horizontal=Side(border_style=None,
#                                 color='FF000000')
#                 )
    #对齐
#  alignment=Alignment(horizontal='general',    #水平对齐，'general', 'right', 'justify', 'centerContinuous', 'distributed', 'fill', 'left', 'center'
#                      vertical='bottom',  #垂直对齐, 'top', 'bottom', 'center', 'distributed', 'justify'
#                      text_rotation=0,    #旋转
#                      wrap_text=False,    #自动换行
#                      shrink_to_fit=False,    #大小缩进以匹配单元格大小
#                      indent=0
#                      readingOrder #文字方向
#                        
#                         )    #左右缩进
#  number_format = 'General'    #设置单元格的分类，@-文本
#  protection = Protection(locked=True,
#                          hidden=False)

    #Font设置字体样式：color-颜色、italic-倾斜、name-字体、size-字号、bold-加粗、underline-下划线
    ftObj = Font(color=colors.RED, italic=True, name='Arial', size=14, bold=True, underline="single", strike=True)
    ws['D1'] = 'Hello World!'
    ws['D1'].font = ftObj
    #可直接设置
    ws['D2'] = 'DDD222'
    #另一种设置颜色的方式
    ws['D2'].font = Font(color="FFBB00")
    
    #Font对象能够复制
    ws['D3'] = 'ddd333'
    ftObj2 = copy(ftObj)
    ftObj2.name = 'Tahoma'
    ws['D3'].font = ftObj2
    
    #样式能够设置给整行或整列，但是这个功能很智障，对已有内容不生效，对空格在关闭文件后才生效，但是我重新去赋值的时候，又变成使用默认的格式
#     colObj = ws.column_dimensions['D'] # 这个函数会把列给隐藏了0.0
#     colObj.font = Font(size=30)
#     rowObj = ws.row_dimensions[1]
#     rowObj.font = Font(underline="single")
#     #关闭文件
#     saveFilename = time.strftime('%Y-%m-%d_%H-%M-%S', time.localtime(time.time())) + '.xlsx'
#     wb.save(saveFilename)
#     wb = load_workbook(filename = saveFilename)
#     ws = wb.get_active_sheet()
#     ws['D4'] = 'ddd444'
    
    #不知道这几个干嘛用的，好像是设置行高和列宽，但是又没有生效
#     ws.page_setup.orientation = ws.ORIENTATION_LANDSCAPE
#     ws.page_setup.paperSize = ws.PAPERSIZE_TABLOID
    ws.page_setup.fitToHeight = 30
    ws.page_setup.fitToWidth = 30

    #名称样式
    #创建名称样式
    highlightNS = NamedStyle(name="highlight")
    highlightNS.fone = Font(bold=True, size=20)
    bd = Side(style='thick', color='000000')
    highlightNS.border = Border(left=bd, top=bd, right=bd, bottom=bd)
    #工作表添加这种样式
    wb.add_named_style(highlightNS)
    #单元格，通过样式对象设置样式
    ws['E1'].style = highlightNS
    ws['E1'] = 'EEE111'
    #单元格，通过样式名称设置样式
    ws['E2'].style = 'highlight'
    ws['E2'] = 'EEE222'
    ws['E3'] = 'EEE333'
    #可以使用系统默认的样式，系统自带的如下：
    # ‘Normal’-常规、‘Good’-好、‘Bad’-差、‘Neutral’-适中
    # ‘Calculation’-计算、‘Check Cell’-检查单元格、‘Explanatory Text’-解释性文本、‘Warning Text’-警告文本、‘Linked Cell’-链接单元格
    # ‘Output’-输出、‘Input’-输入、‘Note’-注释
    # ‘Title’-标题、‘Headline 1’-标题1、‘Headline 2’-标题2、‘Headline 3’-标题3、‘Headline 4’-标题4、‘Total’-汇总
    # ‘Accent1’-强调文字颜色1、‘20 % - Accent1’-20%强调文字颜色1、‘40 % - Accent1’-40%强调文字颜色1、‘60 % - Accent1’-60%强调文字颜色1
    # ‘Accent2’-强调文字颜色2、‘20 % - Accent2’-20%强调文字颜色2、‘40 % - Accent2’-40%强调文字颜色2、‘60 % - Accent2’-60%强调文字颜色2
    # ‘Accent3’-强调文字颜色3、‘20 % - Accent3’-20%强调文字颜色3、‘40 % - Accent3’-40%强调文字颜色3、‘60 % - Accent3’-60%强调文字颜色3
    # ‘Accent4’-强调文字颜色4、‘20 % - Accent4’-20%强调文字颜色4、‘40 % - Accent4’-40%强调文字颜色4、‘60 % - Accent4’-60%强调文字颜色4
    # ‘Accent5’-强调文字颜色5、‘20 % - Accent5’-20%强调文字颜色5、‘40 % - Accent5’-40%强调文字颜色5、‘60 % - Accent5’-60%强调文字颜色5
    # ‘Accent6’-强调文字颜色6、‘20 % - Accent6’-20%强调文字颜色6、‘40 % - Accent6’-40%强调文字颜色6、‘60 % - Accent6’-60%强调文字颜色6
    #‘Percent’-百分比、‘Comma’-货币、‘Comma [0]’-货币[0]、‘Currency’-千位分割、‘Currency [0]’-千位分割[0]
    # ‘Hyperlink’、‘Followed Hyperlink’、‘Pandas’
    
    #设置列宽
    ws['F1'] = 'Life is short, I use python.'
    ws.column_dimensions['F'].width = 60
    
    ws['G1'] = '01234567'
    ws['G1'].number_format = '@'
    print(ws['G1'].number_format)
    #从顶点开始深度优先遍历节点树
#     topFatherBomObj.depth()
    
    #冻结首行
#     ws.freeze_panes = 'A2'
#     #设置单元格样式
#     for rowI in range(1, ws.max_row + 1):
#         for colJ in range(1, ws.max_column + 1):
#             #horizontal：设置左对齐、居中、右对齐；vertical：设置上对齐、居中、下对齐；wrap_text：设置是否自动换行
#             ws.cell(row=rowI, column=colJ).alignment = alignment.Alignment(horizontal='left', vertical='center', wrap_text=True)

    #工作表属性
    # Available properties for worksheets
    # “enableFormatConditionsCalculation”
    # “filterMode”
    # “published”
    # “syncHorizontal”
    # “syncRef”
    # “syncVertical”
    # “transitionEvaluation”
    # “transitionEntry”
    # “tabColor”
    # 
    # 
    # Available fields for page setup properties
    # 
    # “autoPageBreaks” “fitToPage”
    # 
    # 
    # Available fields for outlines
    # “applyStyles”
    # “summaryBelow”
    # “summaryRight”
    # “showOutlineSymbols”
    ws['A1'] = '编码'
    print(ws['E5'].col_idx)
    print(ws['A1'].encoding)

    #保存到文件中
#     filename = topFatherBomObj.bom + time.strftime('_%Y-%m-%d_%H-%M-%S', time.localtime(time.time())) + '.xlsx'
    filename = time.strftime('%Y-%m-%d_%H-%M-%S', time.localtime(time.time())) + '.xlsx'
    wb.save(filename)
    print("Pass")