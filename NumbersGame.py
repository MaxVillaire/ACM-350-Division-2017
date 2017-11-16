#Written By: Maxwell Villaire
#Written for ACM 350+ division 2017
#Question can be found here
#https://www.hackerrank.com/contests/usc-acm-fall-2017-350-division/challenges/the-numbers-game/problem
#Number of test cases
T = int(raw_input())
for i in range(0,T):
	BuellWins = False
	#We need to play this game for each test case
	N = int(raw_input())
	while N > 0:
		#Check if Fenner Wins
		if N == 1:
			BuellWins = False
			break
		#Buell's Turn
		if (N%2) == 0:
			#If N is a power of 2, then divide N by 2
			#Using binary shift right for quick division
			N = N >> 1
		else: 
			#In this case we need to subtract the largest power of 2
			##subtract
			N = N-2**(N.bit_length()-1)
		if N == 1:
			BuellWins = True
			break
		#Fenner's Turn
		if (N%2) == 0:
			#If N is a power of 2, then divide N by 2
			#Using binary shift right for quick division
			N = N >> 1
		else: 
			#In this case we need to subtract the largest power of 2
			##subtract
			N = N-2**(N.bit_length()-1)
	#Print the result
	if BuellWins:
		print "BUELL"
	else:
		print "FENNER"