# Android AI Fitness App

Hello! Welcome to our Android AI Fitness Application developed in Android Studio using mostly Java. 
Here is a brief overview of the project:
<ul>
  <li>Application concept: User provides fitness information
  AI generates a personalized workout plan based on user data</li>
  <li>Features include:
    <ul>
      <li>Calorie counter to track calories consumed in a day</li>
      <li>AI recommended workouts based on user statistics and goals</li>
      <li>Chat feature for additional questions to the AI (AI acts as personal trainer for user)</li>
    </ul>
  </li>
</ul>

<br/>

This project was a team effort, and took about five months from start to finish to complete. Listed below are the members of our team as well as their roles:
 <ul>
  <li>Rodolphe Eugene - Worked on shared preferences to store user information. Previously, a database was utilized, but this was changed for simplicity and easier access to data.
  Also created the tool bar for navigation between screens.
  </li>
  <li>Gabriel Lopez - Grabbed information from the Open AI API and displayed it. Also saved information from Open AI API into database.
  </li>
  <li>Zachary Chair - Created workouts database and workouts class to assist with data. Also created saved workouts screen to display with a RecyclerView a specified layout.
  </li>
  <li>Charles Cahill - Worked on Open AI API integration into the chat screen page. Also made chat screen ui function like a chat.
  </li>
  <li>Zahria Thomas - Created UI for home screen, also created UI for profile screen, displayed shared preferences to home screen, and created calorie counter and their progress bars
</li>
</ul> 


# Sign In Screen

<ul>
  <li>User information is entered and saved using shared preferences.</li>
  <li>Entered information can later be used to update the calorie counter and generate workouts based on the user goals.</li>
  <li>Every other activities have access to saved data in SharedPreferences and can access it by using the correct key.</li>
</ul>
<br/>
<img width="294" alt="Screenshot 2024-02-12 at 6 17 29 PM" src="https://github.com/CharlesC234/MobileDeviceAppsProject/assets/99014706/ba046e44-88ec-45b8-bcc1-7e8cc6db3cf6">

# Home Screen

<ul>
  <li>Home screen is the central access point</li>
  <li>Navigation through a bottom navigation bar</li>
  <li>Displays user information: calorie goal, workout count, and workout consistency days count</li>
</ul>
<br/>
<img width="252" alt="Screenshot 2024-02-12 at 6 17 40 PM" src="https://github.com/CharlesC234/MobileDeviceAppsProject/assets/99014706/4c1f0038-0739-4e16-8ab4-4522fc37d74f">

# Profile Screen

<ul>
  <li>Profile screen provides more user information</li>
  <li>Main focus: Calorie counter displaying the total calories</li> 
  <li>Access to saved workouts generated from the AI</li>
</ul>
<br/>
<img width="275" alt="Screenshot 2024-02-12 at 6 17 51 PM" src="https://github.com/CharlesC234/MobileDeviceAppsProject/assets/99014706/f317f0e5-5627-4fb8-9133-9bd91b05b8f0">

# Chat Screen

<ul>
  <li>OpenAI API integration for user "chat" and questions</li>
  <li>Basic implementation where users ask questions and AI responds</li>
  <li>"Generate a workout" button implemented</li>
  <li>AI's workout information saved to a database and displayed on the user's screen</li>
</ul>
<br/>
<img width="260" alt="Screenshot 2024-02-12 at 6 18 02 PM" src="https://github.com/CharlesC234/MobileDeviceAppsProject/assets/99014706/d1bdd69a-48e3-4ce4-976c-048354f66c86">

# Saved Workouts Screen

<ul>
  <li>Implemented Workouts Database
  <ul>
    <li>ID INTEGER</li>
    <li>NAME STRING</li>
    <li>DESCRIPTION STRING</li>
  </ul></li>
  <li>Implemented RecyclerView
  <ul>
    <li>Grab workout name and display it</li>
    <li>Grab workout description and have it display in dropdown card</li>
    <li>Allow for deletion of workout from database</li>
  </ul></li>
</ul>
<br/>
<img width="253" alt="Screenshot 2024-02-12 at 6 18 12 PM" src="https://github.com/CharlesC234/MobileDeviceAppsProject/assets/99014706/8686f743-0229-4b6f-a41d-f34847090f1c">

# How The Project Evolved

<ul>
  <li>Initial Idea:
  Have an AI generate you personalized workouts through a mobile application</li>
  <li>Over time we added: 
  <ul>
    <li>A way to input your information so the AI can provide the most personalized workout</li>
    <li>A way to store workouts for future reference</li>
    <li>A way for you to count calories in line with your caloric intake goal</li>
    <li>A screen for displaying user information</li>
  </ul></li>
</ul>


# Takeaways 
<ul>
  <li>Good communication was a key takeaway from the project</li>
  <li>Active collaboration was evident throughout the duration</li>
  <li>Effective communication during both smooth progress and roadblocks</li>
  <li>Team members responded respectfully to questions</li>  
  <li>Active assistance and problem-solving among team members</li>
  <li>Successful time management to complete assignments ahead of due dates</li>
  <li>Have different git branches for each member working on the project</li>

</ul>


