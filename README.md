**# Pre-work -** Doooit 

**Doooit** is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: **Jong Chung**

Time spent: **5** hours spent in total

## User Stories

The following **required** functionality is completed:

* User can **successfully add and remove items** from the todo list
* User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list.
* User can **persist todo items** and retrieve them properly on app restart

The following **optional** features are implemented:

* Persist the todo items [into SQLite](http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file
* Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)
* Use a [DialogFragment](http://guides.codepath.com/android/Using-DialogFragment) instead of new Activity for editing items
* Add support for selecting the priority of each todo item (and display in listview item)
* Tweak the style improving the UI / UX, play with colors, images or backgrounds


## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://i.imgur.com/MguHJYZ.gifv' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Project Analysis

As part of your pre-work submission, please reflect on the app and answer the following questions below:

**Question 1:** "What are your reactions to the Android app development platform so far? Compare and contrast Android's approach to layouts and user interfaces in past platforms you've used."

**Answer:** The Android app development platform is one of a kind. Using a structure that includes a model, view, and controller is similar to my coding experiences in Javascript. I've never used a platform where you can edit the user interface in a graphical view as well as a text view in XML. Being able to organize resources, layout, and logic separately helps me understand my code better. Also, being able to call the different layout items and resources from the Java classes, and seeing how everything connects together really draws my attention.

**Question 2:** "Take a moment to reflect on the `ArrayAdapter` used in your pre-work. How would you describe an adapter in this context and what is its function in Android? Why do you think the adapter is important? Explain the purpose of the `convertView` in the `getView` method of the `ArrayAdapter`."

**Answer:** In this context, an adapter will help convert the objects in the ArrayList into view items in the list. The ListView will have its data populated with the help of an adapter. The adapter is important because it automatically handles the displaying and populating of data without having the programmer manually do it. 

The purpose of the 'convertView' in the 'getView' method is to check if there is already an item view (each view item in the ListView) that is inflated, and if there isn't it will inflate a new item view for the ListView.

## Notes

Describe any challenges encountered while building the app:

The hardest part of this project for me was passing data values from DialogFragment to the MainActivity through a listener.

## License

    Copyright [2017] [Jong Chung]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.