**NB! App under production!!!**

# android-math-game

## Description
Answer questions (e.g. 4+7) to move up a level.

## Techniques used
* Dagger2  
* Jetpack (androidx):  
  * MVVM,  
  * Binding,  
  * Navigation.  
	Navigation in app works like this:  
	
	  WelcomeFragment => LevelFragment (contains ViewPager2 wich contains AnswerQuestionsFragments) => WinFragment.  
	  
	  Back press always leads back to WelcomeFragment. This is intended. 
	    (Since back button with Jetpack Navigation and ViewPager[2] doesn't work smoothly together,  
        	a back press is leading out of ViewPager through a callback 
		(see getOnBackPressedDispatcher inside AnswerQuestionFragment).

