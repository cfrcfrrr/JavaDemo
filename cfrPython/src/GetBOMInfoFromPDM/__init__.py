# -*- coding:utf-8 -*-
#中---优化---request范围PDM网站时传入账号和密码
import requests, bs4

class PdmBom():
    def __init__(self, bom, cnDes, usDes, num, loc, allSon):
        self.bom = bom
        self.cnDes = cnDes
        self.usDes = usDes
        self.num = num
        self.loc = loc
        self.allSon = allSon
        self.needSon = []

topfather_bom_url = 'http://www.baidu.com'

res = requests.get(topfather_bom_url)
res.raise_for_status()
print(res.text)

bs_obj = bs4.BeautifulSoup(res.text)
print(bs_obj.prettify()) #格式化输出

if __name__ == '__main__':
    topFatherBom = PdmBom('02301246', '3U', '3U Whole Machine', 1, '', ['03057769', '02351379', '35689134'])
    print(topFatherBom.bom)
    print(topFatherBom.cnDes)
    print(topFatherBom.usDes)
    print(topFatherBom.num)
    print(topFatherBom.loc)
    print(topFatherBom.allSon)