# CSX42: Assignment 5
**Name:** Rashmi A.Badadale

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in csx42-summer-2020-assign5-rashmi1112\textdecorators\src  folder.

## Instruction to clean:

```commandline
ant -buildfile textdecorators/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile textdecorators/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile textdecorators/src/build.xml run -Dinput="input.txt" -Doutput="output.txt" -DmisspelledWords="misspelledWords.txt" -Dkeywords="keywords.txt" -Ddebug="debug_value"
```
Note: Arguments accept the absolute path of the files.

## Description:

The goal of the program is to implement decorator pattern over a text input by attaching corresponding tags to the words those are either the Most frequent words, keywords or misspelled words. The program accepts 5 arguments, the input file from where the text is to be processed, the keywords file name, the misspelled file name, output file name and the debug value.

Datastructures used in the assignment: 

1. Hashmap<String,Integer> : The hashmap is used for storing the words that occur in the input and to keep a track of number of times they occur to detect the most frequent words. The worst case time complexity for searching in hashmap is O(1) in case where the words are equally distributed. Here the values are the words irrespective of their case and values are the number of times they occur in the input file. 

2. ArrayList<Integer> : ArrayList is used for encapsulating the start and end index of word to be replaced in the given string. The worst case time complexity for inserting the indices in the list is O(1) and for search it is O(N), but since we knew we are inserting only 2 elements (start and end index), we can use Arraylist efficiently.

3. Queue<String> : The queue is used for storing the strings from the Keywords and the Misspelled words. This queue is iterated over to replace all occurences of these words with corresponding tags. 

4. StringBuilder : The StringBuilder is used to store the input provided in the input file. The time complexity for populating the input into the StringBuilder is O(N) since every loop leads to insertion of one word into the String builder.  A String  builder is also used to store the output result and then passed on to an output file through a buffered writer.  


## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [08/08/2020]
