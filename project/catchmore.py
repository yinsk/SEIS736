import urllib.request
import urllib.parse
import re
from bs4 import BeautifulSoup

html = urllib.request.urlopen('http://www.wowprogress.com/apoints/us/illidan/char_rating/next/2').read()

#print(html)
#div = re.findall(r'<div class="bar-contents">(.*)</div>', str(html))
#<a data-hint="undead mage" class="character mage" href="/character/us/illidan/Marsold">Marsold</a>

#div = re.findall(r'</td><td><a data-hint=\"(.*?)</nobr>', str(html))
#div = re.findall(r'</td><td><a data-hint=\"(.*?)</td></tr>', str(html))
div = re.findall(r'<tr><td>(.*?)</td></tr>', str(html))

i=0
while i<24:
    line = div[i].split('\" ')
    print(line)
    i = i+1

    #c1 = re.findall(r'</td><td><a data-hint=\"(.*?)', line[0])
    c1 = line[0].replace("</td><td><a data-hint=\"", ' ')
    #print(c1)

    c2 = re.findall(r'\">(.*?)</a>', line[2])
    charname = c2[0]

    if len(line) == 5:
        guildC = re.findall(r'<nobr>(.*?)</nobr>', line[4])
        guild = guildC[0]
    else:
        guild = 'N/A'

    print(c1, charname,  guild)
    print("\n")
