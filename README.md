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

## Data Understanding and preparation:
* Yelp Fusion API Data
    :* thousandrows of restaurant 
    :* Number of restaurants will increase as user queries the system. (Special  mechanism of our recommender system)
    :* Features include: name, zip code; address; category; price; rating; number of reviews 
    :* Address will be used to calculate the accurate travel time
* User Matrix
    :* Need to get information about user preferences
    :* 39 rows, each is 1 user
    :* 23 columns in each row (each column stands for a food restaurants/categories)
    :* Ratings within each column for each user

