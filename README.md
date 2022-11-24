# ToDoList

## About
This is the repository for our COSC310 project. We are creating an Android to-do list app for students.

## Class structure
All layout code is stored in the layout folder as eight xml files.
- activity_main.xml contains the layout for our main screen, including a RecyclerView for to-do items
- todo_item.xml is the layout of an individual to-do item, which are used to populate the RecyclerView
- activity_edit_todo.xml contains the layout for the edit to-do page that users can reach through the context menu after clicking on a to-do
- activity_edit_tag.xml contains the layout for the edit tag screen that users can reach through the context menu after clicking on a to-do
- tag_item.xml is the layout of an individual tag item
- filter_menu_item.xml contains the layout of an individual filter item
- activity_total_grade.xml is the layout for the max grade input page
- activity_grade_received.xml is the layout for the grade received page

All functional code is contained in .java files in the todolist folder.

**MainActivity** controls the main screen. By default, it extends AppCompatActivity, and it extends
our custom **ToDoClickListener**. The onCreate() method contains initialization code. 
New to-do's are created from user input using the createToDo method.
The onEditClick() and onCheckClick() methods are bound to the RecyclerView elements (individual to-dos).

**EditToDoActivity** controls the edit to-do page. Users can navigate to this page by clicking on a to-do.
This triggers the onEditClick() method and sends the to-do array list to the **EditToDoActivity** class.
On the edit-to page, users can change the text of their to-do, and set a due date.

**ToDo** is a serializable class to store information about to-dos.

**ToDoAdapter** is a custom RecyclerViewAdapter. It contains an inner class, **MyViewHolder**, which 
is a custom ViewHolder.

**ToDoClickListener** is an interface implemented by the **MainActivity**. 
It contains the onEditClick() and onCheckClick() method.

**FilterPowerMenuItem** defines each item in a PowerMenu for the filter menu on the main activity.

**EditTagActivity** controls the edit tag page. Users can navigate to this page through the context 
menu that pops up when clicking on the three dots on a task. There, they can add new or existing 
tags to tasks. They can also delete existing tags.

**FilterMenuAdapter** is a custom MenuBaseAdapter. 

**TagAdapter** is a custom RecyclerViewAdapter. It contains an inner class, **MyViewHolder**, which 
is a custom ViewHolder.

**TagClickListener** is an interface implemented by the **AddTagActivity**.
It contains the onDeleteClick() method.

**GradeReceived** is an activity where the user can enter the grade they received on an assignment

**TotalGradeActivity** is an activity where the user can input grade weight of an assignment.

**NotificationSender** is a class that handles sending the notification when the application is not running. It is all back-end and the user does not directly touch it

**TranslateText** contains all the code for translating text using the Google Translate API

**Config** contains the API key for Google Directions Matrix API. It is not available on Git in order to keep the key private

Test code is contained in **EditToDoActivityUnitTest**

## Documentation for Individual Assignment

### <a href="https://cloud.google.com/translate">API #1 - Google Cloud Translation API</a>
I have implemented Google's Cloud Translation API so that users can translate any of their tasks to another language

<img width=300 src="https://user-images.githubusercontent.com/77038122/203679020-b7702b42-2080-4c19-a3e9-f80718e97b25.png"/><img width=300 src="https://user-images.githubusercontent.com/77038122/203679510-29fa6dc8-236e-413f-8847-ec176f389e4b.png"/>

### <a href="https://developers.google.com/maps/documentation/distance-matrix/overview">API #2 - Google Distance Matrix API</a>
I have implemented Google Map's Distance Matrix API so that users can see the time it will take for them to drive to the location of the task

<img width=300 src="https://user-images.githubusercontent.com/77038122/203679822-f49c8996-6e25-4657-a008-c395689ef6cc.png"/><img width=300 src="https://user-images.githubusercontent.com/77038122/203680288-a6234fe9-5667-411a-9f6c-3c93d96a6e59.png"/>

## Compiling our code
There are two ways to compile our code:
1) clone the project in Android studio and run it on an emulator in the app
2) use the [apk file](https://github.com/COSC310-Team12/ToDoList/releases/download/v0.2.0/ToDoList-v0.2.0.apk) from our latest [release](https://github.com/COSC310-Team12/ToDoList/releases) to install the app on an Android phone

