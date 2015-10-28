import urllib.request
import urllib.parse
import re
from bs4 import BeautifulSoup

html = urllib.request.urlopen('http://us.battle.net/wow/en/character/illidan/Abysslight/achievement#').read()

#div = re.findall(r'<div class="bar-contents">(.*)</div>', str(html))
div = re.findall(r'<div class="bar-contents">(.*?)</div>', str(html))

pat = '\xc2\xa0'

for eachP in div:
    print(eachP)
    m = re.findall("[-+]?\d+[\.]?\d*", eachP)
    print(m[0], m[3], m[4])
    print("\n")
