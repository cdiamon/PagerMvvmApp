## Playground I - Menu Code Kata

### Initial configuration

The idea of the first exercise is to design a simple implementation of a Menu feature based on the requirements listed below. Technical implementation is up to you, but all decisions should be listed in the README file with a short description of the reasonings.  We don't expect you to invest more than 2 hours on this exercise.

For the purpose of this task we will assume the following:<br/> 
A Recipe is a data structure with an id, title, and a list of tags associated with it. Examples of tags can be "hot", "quick", "low-calories", etc. Recipes are equal if their ids are equal. A Subscription is a data structure with id, delivery day, and isForFamily properties.

You will be creating and adding behaviors to a Menu model:

- A list of recipes available for selection is provided to the menu. 
- Menu exposes a list of recipes available for selection.
- Menu has a reference to an associated subscription.

### Requirements

- Keep SOLID principles in mind while you are working on this exercise
- Do not develop any UI for this exercise
- Unit and/or integration tests should be part of this exercise

**Selecting recipes in the menu**

- You should be able to mark a single recipe as selected.
- You should be able to mark multiple recipes as selected.
- You should be able to ask the menu how many recipes have been selected.

**Unselecting recipes in the menu**

- You should be able to unselect a single selected recipe.
- You should be able to unselect multiple selected recipes.

**Accessing recipes in the menu**

- You should be able to request a list of selected recipes.
- You should be able to request a list of recipes which have a certain tag.

**Restricting the behavior**

- You should not be able to select more than 3 recipes.

**Modifying the behavior based on subscription type**

- You should be able to select up to 5 recipes if the subscription is for a family.
 
*Note*

- Don’t forget to notify calling code about errors where appropriate.
- The Recipe data structure is final. Please do not add more properties to it.
 
 
## Playground II -  Recipes List View 


The idea of the second exercise is to create a simple single-page application showcasing the recipes. Please treat this as a production code and feel free to choose design patterns you are comfortable with. We don't expect you to invest more than 3 hours on this exercise. 

### Requirements

- Build a single view application that shows a list of recipes. 
- Implement data loading, basic UI, error handling. 
- Keep in mind code readability, scalability, and maintainability when making implementation decisions. 
- Provide README with justifications and testing strategies. 

The list of recipes should be loaded via HTTP request using this link - [recipes.json](https://hf-android-app.s3-eu-west-1.amazonaws.com/android-test/recipes.json)

**There is no need to build:**

- Stylish UI, basic and readable is enough,
- Navigation,
- Caching,
- Write UI or unit tests.

**User Interface**

- The app should show a single view.
- At launch, the app should show a loading spinner while it gets the data from the source. 
- When the data is fetched, the app should hide the loading spinner and show the recipes’ view.
- Show the current date in “dd  MMM” format (20 Aug) with text size 18sp as a first element of the recipes list view.
- Recipes in the list are presented as cards with image, name and headline. The recipe name should be in bold with text size 16sp.
- If an error occurred, you should show a snackbar with a short description of the issue.

