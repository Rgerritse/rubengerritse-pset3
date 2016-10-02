# Names (4)
	The class and variable names always describe their function given their context. They consistently
	use camelCase and are not unnecessarily long.

# Headers (2)
	Class-header comments are usually present and (very) briefly describe the function of the class, but 
	do not describe how to use it.

	IMPROVEMENT: 
		As an example, the current header comment in MyAdapter.java is:

		/**
		 * Created by ruben on 22-9-16.
		 * Adapter for a RecyclerView.
		 */		

		I feel this could be expanded with a sentence about what the tasks adapter performs, like setting
		the text of an entry to the value stored in the data set.

# Comments (2)
	Comments are almost exclusively used above functions to describe what they do. They are rarely used 
	explain code decisions/structure or highlight the expected behaviour in edge cases.

	IMPROVEMENT:
		The following code block does not have a comment:

		if (!movie.getString("Poster").equals("N/A")) {
            URL imageUrl = new URL(movie.getString("Poster"));
            Bitmap bmp = new ImageCollector().execute(imageUrl).get();
            poster.setImageBitmap(bmp);
        } else {
            poster.setVisibility(View.GONE);
        }

        To someone who made the assignment, it is obvious that this code is used to download the poster if
        it exists, and hide the ImageView if it does not. Someone else, however, will not know that the 
        OMDB API returns "N/A" for poster if it doesn't know a poster for a film. For this reason I feel
        a comment here is necessary here to describe that behaviour.

    IMPROVEMENT:
    	In MoviePage.java there is a nested for loop with the same control statement. From the function
    	header comment and the text it is clear that this code adds a film to the watch list, but without
    	a comment on how the code does this, I find myself unable to understand why a nested for loop is
    	required to add a JSONObject to a JSONArray.


# Layout and Formatting (8)
	The methods are laid out in a readable way, using indentation and whitespace that is consistent throughout
	all files and in line with platform conventions.

	IMPROVEMENT:
		The functions auto-created by android studio do not have a space between the ')' and "{". Adding one
		would make the code look better. Example:

		public boolean onCreateOptionsMenu(Menu menu){
		vs.
		public boolean onCreateOptionsMenu(Menu menu) {


# Flow (4)
	The flow is clear. Functions do not do more than they are stated to and code duplication is essentially
	non-existant.

# Idiom (3)
	The chosen control structures are appropriate for the situation. Libraries are used in situations
	that call for them. 

	IMPROVEMENT:
		In this code, exceptions are caught seperately. While this is good practice, the action performed
		(print stack trace) is the same for every exception. In addition to printing the stack trace, it
		would be a good idea to log the suspected cause of the exception for quicker debugging.

# Expressions (3)
	Expressions contain a certain degree of nesting, but not to the point that it impacts their readability.
	When they are long it is due to the roundabout way that the android libraries work.

	IMPROVEMENT:
		In the function changeStatus in MoviePage.java we find the following code:

		SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();

        In this function the variable pref is not used, except for getting the editor. In this situation I 
        would simply put:

        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("MyPref", 0).edit();

        Despite the line being slightly longer, I find it no harder to read.


# Decomposition (4)
	The code is split into clearly defined routines which each perform a single task. Shared variables 
	are only used when they represent properties of a class.

# Modularization (4)
	The code consists of six modules. Three modules are activities, two modules are asynchronous tasks 
	and one module is an adapter. Since they have such clearly defined tasks, communication between them 
	is limited to Intents and task executions. The modules all contain a small number of routines.

# General
	REMARK:
		Storing the watch list as a JSON string containing the responses from the OMDB API is smart, because
		it removes the need to query the API for every film when loading the watch list. A disadvantage
		of doing this is that, as far as I can tell, the information will never be updated. Most film
		details are not subject to change, but for many films posters are missing and it would be a nice
		addition to have these appear in the app if they are ever added.

	REMARK:
		Using the return value of doInBackground to return the JSON string instead of using onPostExecute
		is a very clean way of handling it. Had I thought of doing this in my app I would have been able to 
		merge two of my AsyncTasks which would have improved the code quality.

	
