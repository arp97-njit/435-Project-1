import random


def getRandomArray(n):
    rtnArray = []
    while len(rtnArray) <= n:
        randNum = random.randrange(0,100)
        if (randNum not in rtnArray):
            rtnArray.append(randNum)
    return rtnArray


