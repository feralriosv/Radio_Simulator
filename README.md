
PROJECT EXAMPLE INTERACTION:

1 > add 2:Alice:Spam:200:1

2 > add 40:Bob:Fubar:300:2

3 > peek

4 00002:Alice:Spam:200:200

5 > list

6 00002:Alice:Spam:200

7 00040:Bob:Fubar:300

8 > add 2:Alice:Spam:200:1

9 > add 13:Bob:FizzBuzz:400:0

10 > add 2:Alice:Spam:200:1

11 > list

12 00013:Bob:FizzBuzz:400

13 00002:Alice:Spam:200

14 00002:Alice:Spam:200

15 00002:Alice:Spam:200

16 00040:Bob:Fubar:300

17 > remove 2

18 Removed 3 songs.

19 > list

20 00013:Bob:FizzBuzz:400

21 00040:Bob:Fubar:300

22 > add 2:Alice:Eggs:100:0

23 > list

24 00013:Bob:FizzBuzz:400

25 00002:Alice:Eggs:100

26 00040:Bob:Fubar:300

27 > history

28 > play 200

29 > peek

30 00013:Bob:FizzBuzz:400:200

31 > next 5:Alice:Topsong:100

32 > peek

33 00013:Bob:FizzBuzz:400:200

34 > list

35 00013:Bob:FizzBuzz:400

36 00005:Alice:Topsong:100

37 00002:Alice:Eggs:100

38 00040:Bob:Fubar:300

39 > skip

40 > peek

41 00005:Alice:Topsong:100:100

42 > play 300

43 > peek

44 00040:Bob:Fubar:300:200

45 > history

46 00005:Alice:Topsong:100

47 00002:Alice:Eggs:100

48 > quit

