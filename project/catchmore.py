import urllib.request
import urllib.parse
import re
from bs4 import BeautifulSoup

html = urllib.request.urlopen('http://www.wowprogress.com/apoints/us/illidan/char_rating/next/1').read()

#print(html)
#div = re.findall(r'<div class="bar-contents">(.*)</div>', str(html))
#<a data-hint="undead mage" class="character mage" href="/character/us/illidan/Marsold">Marsold</a>

#div = re.findall(r'</td><td><a data-hint=\"(.*?)</nobr>', str(html))
#div = re.findall(r'</td><td><a data-hint=\"(.*?)</td></tr>', str(html))
div = re.findall(r'<tr><td>(.*?)</td></tr>', str(html))

i=0
while i < 2:
    # basic line split
    line = div[i].split('\" ')
    print(line)
    i = i+1

    # record 0 : rank, race, class,
    # c1 = re.findall(r'</td><td><a data-hint=\"(.*?)', line[0])
    c1 = line[0].replace("</td><td><a data-hint=\"", ' ')
    r1 = c1.split(' ')

    '''test for c1
    print(len(r1))
    for eachR in r1:
        print(eachR)
        print('\n')
    '''

    charRank = r1[0]
    # blood elf and night elf to bloodelf and nightelf
    if len(r1) == 4:
        race = r1[1]+r1[2]
        charClass = r1[3]
    else:
        race = r1[1]
        charClass = r1[2]

    # end of record 0
    # skip record 1
    # start of record 2
    c2 = re.findall(r'\">(.*?)</a>', line[2])
    charname = c2[0]

    # start of battlecatch
    try:
        charLinkV = re.findall(r'us(.*?)\">',line[2])
        charLink = ''.join(charLinkV)
        #print(str(charLink))
        href = urllib.request.urlopen('http://us.battle.net/wow/en/character/'+ charLink + '/achievement#').read()
        char = re.findall(r'<div class="bar-contents">(.*?)</div>', str(href))

        pat = '\xc2\xa0'

        linein = ""

        for eachP in char:

            m = re.findall("[-+]?\d+[\.]?\d*", eachP)
            record = m[0], m[3], m[4]

            strRecord = str(m[0] )+" , "+str(m[3])+" , " + str(m[4]) + "\t"
            linein = linein + strRecord
    except:
        pass
    # end of battlecatch
    # end of record 2
    # skip record 3
    # start of record 4

    if len(line) == 5:
        guildC = re.findall(r'<nobr>(.*?)</nobr>', line[4])
        guild = guildC[0]
    else:
        guild = 'N/A'

    # end of record 4
    print( charRank + "\t" + race + "\t" + charClass + "\t" + charname + "\t" + guild + "\t" + linein +"\n")

