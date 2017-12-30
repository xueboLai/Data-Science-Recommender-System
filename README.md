# Data-Science-Collaborative-Food-Recommender-System

## Overview

This project aims to create a collaborative food/restaurants recommender system by integrating multiple machine learning algorithms. The model tries to provide accurate prediction and suggestions to what users might like. 
<br>
The project follows the procedures of "Cross-industry Standard Process For Data Mining" (CRISP-DM), which is a data mining process that commonly uses in industries to tackle problems. CRISP-DM breaks problems into ___six phases___: 
* Business Understanding
* Data Understanding
* Data Preparation
* Modeling
* Evaluation
* Deployment
<br>
Each phase has been described in detailed below.
<br>
<br>
# Developers:
Xuebo Lai, Isaac Blinder, Christian Miljkovic, Arjun Madgavkar, Greg Ha
<br>

## Business Understanding

* Background: currently people eat inefficiently because they are lazy and have limited time/money
* Objectives: create a recommendation system that helps people eat according to their budget, according to their health goals, and within their dietary restrictions
* Success: over 60% of recommendations given are used on a daily basis

## Data Understanding:
<br>
# Data Collection:

* In order to obtain information on the restaurants in Manhattan, data are collected from Yelp on thousands of restaurants in Manhattan. As well as surveyed over 100 people in order to know their preferences and backgrounds.
<br>
<br>
# Data Description:

* Yelp Fusion API Data
    * thousandrows of restaurant 
    * Number of restaurants will increase as user queries the system. (Special  mechanism of our recommender system)
    * Features include: name, zip code; address; category; price; rating; number of reviews 
    * Address will be used to calculate the accurate travel time
* User Matrix
    * Need to get information about user preferences
    * 16 rows, each is 1 user
    * 23 columns in each row (each column stands for a food restaurants/categories)
    * Ratings within each column for each user
<br>
# Data Preparation:
* cleaned the data for missing values that might be within the Yelp API Restaurant data. For numerical data, such as the ratings, averages based on user data were used. 

* created aggregated categories for the many different types of restaurants the Yelp API contained.

* Some of the restaurants in the user data were missing from the Yelp API so their information had to be entered manually

* Then performed feature selection to remove unnecessary features such as “Phone Number” and low variance features such as location (since we were just focusing on NY)

* Next mapped all categorical data to numerical values, and fill in any empty values or Na’s with 0 or the mean depending upon the feature.

* A particularly tricky feature was the restaurant categories as many had several, thus split and expanding the data frame was needed in order to complete this step.


Sample parsed data from Yelp before further processing:
![list create](Demo/beforeProcess.png)
<br>
Sample parsed Data after processing (feature selection, normalization, quantification, etc.)
![list create](Demo/YelpClean.png)
<br>
Sample aggregated categoies for restaurants:
![list create](Demo/Aggregated.png)
<br>
Sample users to restaurants category matrix:
![list create](Demo/PToC.png)
<br>
Finding the best group number (way to group) by using Silhoutte Score:
![list create](Demo/logFile.png)
<br>
Clustering output
![list create](Demo/ClusteringOutput.png)
<br>













