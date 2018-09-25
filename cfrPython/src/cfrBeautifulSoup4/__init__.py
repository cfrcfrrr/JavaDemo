#-*-coding:utf-8-*-
import requests, bs4, re

#通过requests从网页获取bs4对象
def getBSFromURL():
    exampleRes = requests.get('http://www.dytt8.net/')
    exampleRes.raise_for_status()
    exampleRes.encoding = 'gb2312'
    exampleSoup = bs4.BeautifulSoup(exampleRes.text, 'html.parser')
    return exampleSoup

#从文件获取bs4对象
def getBSFromFile():
    exampleFile = open('example.html', encoding = 'UTF-8')
    exampleSoup = bs4.BeautifulSoup(exampleFile, 'html.parser')
    return exampleSoup

#获取标签名称、属性字典、属性值、内容
def getTagMes(exampleSoup):
    #name属性返回标签本身的名称
    print(exampleSoup.a.name) #a
    #attrs属性以字典形式返回标签的属性
    #为什么class那里会多出来[]？
    print(exampleSoup.a.attrs) #{'href': 'http://example.com/elsie', 'class': ['sister'], 'id': 'link1'}
    #取出属性的值,也可以使用get方法，等价print(exampleSoup.a.get('class'))
    print(exampleSoup.a.attrs['class']) #['sister']
    #string属性返回标签的内容，返回值为NavigableString对象
    print(exampleSoup.p.string) #The Dormouse's story
    print(type(exampleSoup.p.string))#<class 'bs4.element.NavigableString'>
    #如果标签内容为注释，string属性返回会自动注释掉注释符号，并且返回值类型为Comment对象
    print(exampleSoup.a.string) #Elsie
    print(type(exampleSoup.a.string))#<class 'bs4.element.Comment'>

#获取标签子节点、子孙节点、父节点、祖宗节点、前后兄弟节点、前后全部兄弟节点、前后节点、前后全部节点
def getAssociatedTag(exampleSoup):
    #contents属性以列表形式返回子节点，如果没有子节点，则变成返回值
    print(exampleSoup.head) #<head><title>The Dormouse's story</title><p class="title" name="dromouse"><b>The Dormouse's story</b></p></head>
    print(exampleSoup.head.contents) #[<title>The Dormouse's story</title>, <p class="title" name="dromouse"><b>The Dormouse's story</b></p>]
    print(exampleSoup.head.contents[0]) #<title>The Dormouse's story</title>
    #也可通过遍历children属性返回的list生成器对象获取子节点
    for sonTag in exampleSoup.head.children:
        print("sonTag:" + str(sonTag))
    #descendants属性返回所有子孙节点的list生成器，会按深度优先显示子标签及其内容
    for childTag in exampleSoup.head.descendants:
        print(childTag)
    #parent属性返回父节点
    print(exampleSoup.b.parent) 
    #parents属性返回所有父节点
    for forefathersTag in exampleSoup.b.parents:
        print(forefathersTag.name)
    #下一个兄弟节点，没有时返回None。因为换行和空白也可以被视为一个节点，因此结果经常为空白或换行。
    print(exampleSoup.p.next_sibling)
    #上一个兄弟节点
    print(exampleSoup.title.previous_sibling)
    #所有后兄弟节点
    for afterBroTag in exampleSoup.a.next_siblings:
        print(afterBroTag)
    #所有前兄弟节点
    for beforeBroTag in exampleSoup.a.previous_siblings:
        print(beforeBroTag)
    #后一个节点（不分层级，相当于从当前标签头开始的下一个标签，即使是子标签）
    print(exampleSoup.p.next_element) #<b>The Dormouse's story</b>
    #前一个节点，返回值
    #为什么一个返回的是父标签，一个返回的是上一个标签的内容
    print(exampleSoup.b.previous_element) #<p class="title" name="dromouse"><b>The Dormouse's story</b></p>
    print(exampleSoup.title.previous_element) #The Dormouse's story
    for nextAllTag in exampleSoup.p.next_elements:
        print(nextAllTag)
    for preAllTag in exampleSoup.title.previous_elements:
        print(preAllTag)

def findAllMethodNameParaIncomeFunc(tag):
    return tag.has_attr('style') and tag.has_attr('id')

#多条件筛选，findall方法，find_all(name, attrs, recursive, text, **kwargs)
def findAllTag(exampleSoup):
    #name参数，可直接传标签名称。字符串节点会被忽略，后面相同
    for eachTag in exampleSoup.find_all('p'):
        print(eachTag)
    #name参数，传正则表达式
    for eachTag in exampleSoup.find_all(re.compile("^b")): #以b开头的标签都会被找到，如b、body
        print(eachTag.name)
    #name参数，传入列表，则会找到匹配列表任一元素的所有标签
    for eachTag in exampleSoup.find_all(["a", "b"]):
        print(eachTag)
    #name参数，传入True，则匹配所有标签，当然还是不包括字符串节点
    #name参数，传方法
    #这里没搞懂，到底怎么用
    for eachTag in exampleSoup.find_all('span'):
        if exampleSoup.find_all(findAllMethodNameParaIncomeFunc(eachTag)) == True:
            print(eachTag)
    #keyword参数
    print(exampleSoup.find_all(id='bottom_container'))
    #如果属性名是python关键字，如class，则再最后加上下划线“_”，class_
    print(exampleSoup.find_all(class_='qrcode-text'))
    #keyword参数，同时匹配多个属性值，需都满足
    print(exampleSoup.find_all(class_="s-bottom-ctner", id="bottom_container"))
    #attrs参数，有些tag属性不能搜索，如HTML5中的data-*属性，可使用attrs参数，传入字典来搜索
    print(exampleSoup.find_all(attrs={"data-foo": "value"}))
    #text参数，匹配标签内容，可接受字符串、正则表达式、列表、True，必须全匹配，并且只返回匹配到的内容部分
    #好像中文匹配有点问题？如百度首页，匹配“意见反馈”可匹配到，匹配“高级搜索”匹配不到？
    print(exampleSoup.find_all(text="意见反馈"))
    #limit参数，限制返回的数量
    print(exampleSoup.find_all('a', limit=2))
    #recursive参数，设置为False则只搜索直接子节点。find_all方法默认是搜索所有子孙节点
    print(len(exampleSoup.head.find_all("link", recursive=False)))
    #find方法直接返回结果，find_all方法返回结果的列表
    #其实没看懂，等后面再模拟数据试一下，find函数的返回值的len，好像是嵌套的层数？
    print(len(exampleSoup.head.find_all('link')))
    print(exampleSoup.head.find('link'))
    #find_parent方法只搜索当前节点的父节点
    print(exampleSoup.link.find_parent('head'))
    #find_parents方法只搜索当前节点的全部父辈节点
    print(exampleSoup.link.find_parents('head'))
    #find_next_sibling返回符合条件的第一个后兄弟节点
    print(exampleSoup.link.find_next_sibling('p'))
    #find_next_siblings返回符合条件的所有后兄弟节点
    print(exampleSoup.link.find_next_siblings('p'))
    #find_previous_sibling返回符合条件的第一个前兄弟节点
    print(exampleSoup.link.find_previous_sibling('p'))
    #find_previous_siblings返回符合条件的所有前兄弟节点
    print(exampleSoup.link.find_previous_siblings('p'))
    #find_next返回符合条件第一个后节点
    print(exampleSoup.link.find_next('p'))
    #find_all_next返回符合条件的所有后节点
    print(exampleSoup.link.find_all_next('p'))
    #find_previous返回符合条件的第一个前节点
    print(exampleSoup.link.find_previous('p'))
    #find_all_previous方法返回符合条件的所有前节点
    print(exampleSoup.link.find_all_previous('p'))

#使用select方法筛选
def selectTag(exampleSoup):
    #通过标签名查找
    print(exampleSoup.select('title'))
    #通过类名（class属性的值）查找，注意，开头需要加个点“.”，同写CSS时
    print(exampleSoup.select('.show-polution-name')) #[<em class="show-polution-name">良</em>]
    #通过id查找，注意，开头需要加个“#”，同写CSS时
    print(exampleSoup.select('#s_strpx_span1'))
    #组合查找，如果条件是同层级的，则条件之间不用分开，同CSS
    print(exampleSoup.select('em.show-polution-name'))
    #如果条件不同层级，则用空格分开，同CSS
    print(exampleSoup.select('span .show-polution-name')) #查找span标签的子孙节点中类名为show-polution-name的标签
    #子标签查找
    print(exampleSoup.select("head > title"))
    #属性查找
    print(exampleSoup.select('em[class="show-polution-name"]'))
if __name__ == '__main__':
    exampleSoup = getBSFromFile()
    #格式化输出
#     print(exampleSoup.prettify())
    #标签筛选，返回tag对象，但只会筛选出符合条件的第一个
#     print(exampleSoup.a) #<a class="sister" href="http://example.com/elsie" id="link1"><!-- Elsie --></a>
    