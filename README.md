# SmallAndroidApp
This is a very small android project which was build withing couple days to meet the vacancy deadlines requirments. 
This project covers the next topics:
- Working with Site APIs
- Multithreaded programming
- JSON Objects
- ListView Adapters

Video of project usage: https://drive.google.com/file/d/1d5eEz20Ht7v6GglQjvqEuLGQ_F509jE8/view?usp=sharing

The application consists of 3 screen views:
1. First screen which appears when application is launched. 
On this screen there are text field to type keyword and button to launch second screen with results.
By pressing the button the second screen will be shown with results of news from New Yourk Times site which coresponds with keyword.
2. Second screen consists of list of news related to the keyword. ListView with custom adapter which extends BaseAdapter 
were used to build this screen. To load data from API AsyncTask extention were used.
Only 10 news are shown on the second screen at a time. 
To see another 10 news button "Next Ten Res" must be pressed. 
To see any of the news from the list the row consisting the news must be tapped.
3. Third screen shows the news selected from previouse screen. 

