#Written By: Maxwell Villaire
#Written for ACM 350+ division 2017
#Question can be found here
#https://www.hackerrank.com/contests/usc-acm-fall-2017-350-division/challenges/tingles-best-brother
#This problem is basically just the josepus problem
#In a circle of N people, every other person is removed, who is the last alive?
N = int(raw_input())
#n=2^m+l - solve for l - l = n - 2^m
l = N - 2**(N.bit_length()-1)
#f(n) = 2l + 1
print 2*l + 1