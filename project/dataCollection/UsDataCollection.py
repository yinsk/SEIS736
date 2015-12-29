import urllib.request
import urllib.parse
import re

def main():
    # change name and page here:
    realmN = "Tol Barad"
    page = 18
    #############

    realm = realmN.lower().replace(" ", "-").replace("\'", "-")
    html = urllib.request.urlopen('http://www.wowprogress.com/apoints/us/'+ realm + '/').read()

    LoopPages(html, realm, page)

    print("done")


def LoopPages (html, realm, page):
    fo = open(realm + ".txt", "w+")

    j = 0
    while j < page:

        div = re.findall(r'<tr><td>(.*?)</td></tr>', str(html))

        i = 0
        while i < 23:
            # basic line split
            line = div[i].split('\" ')
            print(line)
            i = i+1

            # record 0: rank, race, class,
            c1 = line[0].replace("</td><td><a data-hint=\"", ' ')
            r1 = c1.split(' ')

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

            #strname = str(charname)
            #star of battlecatch

            linein =  battleCatch(line[2])

            # end of battlecatch
            # end of record 2
            # skip record 3
            # start of record 4

            if len(line) == 5:
                guildC = re.findall(r'<nobr>(.*?)</nobr>', line[4])
                guild = guildC[0]
            else:
                guild = 'N/A'

            resultLine = charRank + "\t" + race + "\t" + charClass + "\t" + charname+ "\t"+ realm + "\t" + guild + "\t" + linein + "\n"
            print(resultLine )
            # print("\n")
            fo.write(resultLine)

        q = str(j)
        html = urllib.request.urlopen('http://www.wowprogress.com/apoints/us/'+ realm + '/char_rating/next/'+q).read()
        linein=""
        j = j + 1

    fo.close()

def battleCatch(line):
     try:
        try:
            charLinkV = re.findall(r'us(.*?)\">',line)
            charLink = charLinkV[0]

            href = urllib.request.urlopen('http://us.battle.net/wow/en/character/'+ charLink + '/achievement').read()

        except urllib.error.URLError:
            # some realm have different name on wowprogresss.com and battle.net
            charLink = charLink.replace("-", "", 1)

            print("realm name changed")

            href = urllib.request.urlopen('http://us.battle.net/wow/en/character/'+ charLink + '/achievement').read()

        else:
            pass

        char = re.findall(r'<div class="bar-contents">(.*?)</div>', str(href))
        faction = re.findall(r'profile-wrapper profile-wrapper-(.*?)">\\n\\n\\t\\t', str(href))

        linein = faction[0] + "\t"

        for eachP in char:
            m = re.findall("[-+]?\d+[\.]?\d*", eachP)

            strRecord = str(m[0])+"\t"
            linein = linein + strRecord
     except:
        print("PlayerNotFound")
        linein = "PlayerNotFound"

     return linein


if __name__ == "__main__":
    main()
