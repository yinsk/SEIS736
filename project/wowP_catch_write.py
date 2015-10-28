import urllib.request
import urllib.parse
import re


html = urllib.request.urlopen('http://www.wowprogress.com/apoints/us/illidan/').read()


div = re.findall(r'<tr><td>(.*?)</td></tr>', str(html))

fo = open("illidan.txt","w+")

i=0
while i<24:
    line = div[i].split('\" ')
    i = i+1

    c1 = line[0].replace("</td><td><a data-hint=\"", ' ')

    c2 = re.findall(r'\">(.*?)</a>', line[2])
    charname = c2[0]

    if len(line) == 5:
        guildC = re.findall(r'<nobr>(.*?)</nobr>', line[4])
        guild = guildC[0]
    else:
        guild = 'N/A'

    c = c1 + "," + charname + "," + guild + "\n"
    print(c)
    print("\n")
    fo.write(c)




fo.close()
