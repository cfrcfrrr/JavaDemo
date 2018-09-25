import io, sys
import urllib
sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf8')
res =urllib.request.urlopen('http://www.baidu.com')
htmlBytes = res.read()
print(htmlBytes.decod('utf-8'))