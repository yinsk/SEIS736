import urllib.request
import urllib.parse
import re

fo = open("illidan.txt", "w+")

j = 0
while j < 15:

    j = j+1

    q = str(j)

    html = urllib.request.urlopen('http://www.wowprogress.com/apoints/us/illidan/char_rating/next/'+q).read()

    div = re.findall(r'<tr><td>(.*?)</td></tr>', str(html))

    i=0
    while i<23:
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

        c = c1 + "," + charname + "," + guild + "\n"
        #print(c)
        #print("\n")
        fo.write(c)
print("done")
fo.close()

