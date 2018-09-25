#-*-coding:utf-8-*-
import requests

#get，修改编码
def getChangeEncoding():
    url = 'http://www.dytt8.net/'
    res = requests.get(url)
    print(res.apparent_encoding)
    res.encoding = 'gb2312' #编码设置为页面中的字符编码，解决中文显示问题
    print(res.text)
    
#get，带参数
def getWithPara():
    url = 'http://dict.baidu.com/s'
    res = requests.get(url, params={'wd':'python'})
    print(res.url) #http://dict.baidu.com/s?wd=python
    print(res.text)

#post，登录，这里面还有好多东西没了解清楚
def postLogin():
    url = 'https://accounts.douban.com/login'
    #传递参数的字典键和值，需要通过开发者工具，然后登录页面，去抓包来分析取出来，通常是取出类型为POST的页面即可。
    loginData={
        'form_email': '1021356954@qq.com',
        'form_password': 'dbhaohaoxuexiC'
    }
    doubanRes = requests.post(url, data=loginData)
    doubanRes.raise_for_status()
    print(doubanRes.text)

if __name__ == '__main__':
    getChangeEncoding()