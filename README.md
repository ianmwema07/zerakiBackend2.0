# zerakiBackend2.0
This is the backend implementation of the Zeraki Backend task.

## PROCESS DEFINITION

To actually see the entire results of what has been done, one needs to follow the steps below : 

* Register a user whose credentials will be used to generate the token
  * Sample payload is : {
    "userName":"Ian Mwema",
    "password":"123456"
    }
  * API URL localhost:8082/auth/register : **Method :** post
* Request for an auth token for which you will be able to authorize request
  * Select the Bearer token option
  * Sample payload : {
    "userName":"Ian Mwema",
    "password":"123456"
    }


* The next steps involve :
  * Creating a new user  with the endpoint below
    * localhost:8082/api/user/create
  * Creating a new Lesson  with the endpoint below
      * localhost:8082/api/lesson/create
  * Creating a new user  with the endpoint below
      * localhost:8082/api/exercise/create
  * Assigning the user lessons with the endpoint below
    * localhost:8082/api/user/assignlesson
    * **Important to note :** When assigning a lesson to a user both the user id and the lesson id should be existent in the DB.
  * Assigning the Lessons exercises
    * localhost:8082/api/lesson/lesson/exercise
    * **Important to note :** When assigning an exercise to a lesson both id's should be present.
  * Adding user scores
    * localhost:8082/api/score/create
    * **Important to note :** When adding a score both the userId and exerciseId should be existent in the DB
  * Getting the userprogress
      * The user progress is a combination of a data gotten from a join table (view) from the db;
      * The query of the view is as follows : 
      *     create or replace view public.user_progress as SELECT row_number() OVER () AS id, au.id as user_id, au.user_name, e.name AS exercise_name, es.marks,  es.remarks FROM exercise_score es JOIN app_user au ON es.user_id = au.id JOIN exercise e ON es.exercise_id = e.id;
      * localhost:8082/api/userprogress/getUserProgress/352

For any clarification please feel free to reach out [Email Me](mailto:ianmwema07@outlook.com)



