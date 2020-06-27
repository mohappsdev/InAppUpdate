[![](https://jitpack.io/v/mohappsdev/InAppUpdate.svg)](https://jitpack.io/#mohappsdev/InAppUpdate)
# Simple implementation of Play Store's InAppUpdate

## HOW TO USE


Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Add the dependency

	dependencies {
	        implementation 'com.github.mohappsdev:InAppUpdate:v1.0.0'
	}


   #### Create [ForceUpdateActivity](https://github.com/mohappsdev/InAppUpdate/blob/master/app/src/main/java/mohapps/iaudemo/activity/ForceUpdateActivity.java)
   #### Modify your [MainActivity](https://github.com/mohappsdev/InAppUpdate/blob/master/app/src/main/java/mohapps/iaudemo/activity/MainActivity.java)
   #### Create [BaseActivity](https://github.com/mohappsdev/InAppUpdate/blob/master/app/src/main/java/mohapps/iaudemo/activity/BaseActivity.java)
   #### Both activities extend BaseActivity
   #### Add button_in_app_update (visibility = GONE) in your activity_main
   #### Specify your settings in [Config](https://github.com/mohappsdev/InAppUpdate/blob/master/app/src/main/java/mohapps/iaudemo/config/Config.java)

## HOW IT WORKS
   #### button_in_app_update will be shown on main activity when there is an update available and when user taps it update dialog will be shown (Flexible update)
   #### ForceUpdateActivity will be opened when update version meets the settings you have specified and update dialog will be shown and user has to update to use app (Immediate update)
   

## FORCE UPDATE
   #### There are occasions where you want to force users to update to your app's latest version
   #### 1. A feauture rich update you don't want users to miss out
   #### 2. An important security fix
   #### 3. Changes in backend where outdated versions of your app are affected

## FORCE UPDATE STRATEGIES
   ### 1. When your new update's version ends with a specific number
   #### To use this strategy you have to specify a positive number for FORCE_UPDATE_LAST_DIGIT (no limit on digits) and uncomment this line
   FORCE_UPDATE_STRATEGY_LIST.add(ForceUpdateStrategy.LAST_DIGIT);
   #### example: If you set it as 4, updates with version code of *4 such as 4, 14, 24, 34, ..., 104, 114, ... will be forced to users
   #### example: If you set it as 23, updates with version code of *23 such as 23, 123, 223, 323, ..., 1023, 1123, ... will be forced to users
   #### and so on...
   #### this strategy allows you to force update to all your users, all you have to do is make your new update's version number end with your specified number

   ### 2. When your new update's version number is a major change from installed version
   #### To use this strategy you have to specify a positive number for FORCE_UPDATE_MAJOR_LENGTH (no limit on digits) and uncomment this line
   FORCE_UPDATE_STRATEGY_LIST.add(ForceUpdateStrategy.MAJOR_CHANGE);
   #### example: If you set it as 1, updates where 1 digit from left chenged such as 1 to 2, 12 to 22, 100658 to 200033 will be forced to users
   #### example: If you set it as 2, updates where 2 digits from left chenged such as 11 to 22, 120 to 230, 100658 to 210033 will be forced to users
   #### and so on...
   #### Also if your version's length has increased update will be forced
   #### example: updates where version's length increased such as 9 to 10, 99 to 100 will be forced to users
   #### this strategy allows you to prevent your users from using a very outdated version
   
   ## LICENSE

    Copyright 2020 mohapps

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.



   
