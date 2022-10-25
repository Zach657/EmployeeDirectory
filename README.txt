## Build tools & versions used
Gradle version - 7.0.4
Compile For - Android 32
Minimum Supported - Android 26

## Steps to run the app
When you open the application it automatically pulls the employee directory from employees.json url. So there are no steps aside from running / opening the app.

## What areas of the app did you focus on?
I wanted to ensure I followed modern best practices so I spent the most time researching what those were. I work with an app that still uses async tasks and progress dialogs so I had a bit of catching up to do.
I also spent some time looking into some "nice to haves" that I haven't utilized before (namely the pull to refresh functionality). I also spent some time on making the UI look decent, at least in light theme.

## What was the reason for your focus? What problems were you trying to solve?
Despite phones becoming more and more powerful, I still think it's vitally important to write performant code, as well as avoiding anything intensive on the UI thread. An unresponsive app is about the worst thing you can have from a UX perspective (short of a crash).

## How long did you spend on this project?
I took the full 8 hours. If you include the time I spent catching up on modern best practices it was probably longer.

## Did you make any trade-offs for this project? What would you have done differently with more time?
I would have liked to get my unit tests working a little cleaner. I would have liked to have been able to read in json files for the purpose of testing my EmployeeEntity parser.

## What do you think is the weakest part of your project?
The unit tests could use some work, but also I think the app could really use a configuration page for the purpose of testing multiple urls. It would have been nice to implement something that was passable from adb command line to fill the requirement of not creating any other screens.

## Did you copy any code or dependencies? Please make sure to attribute them here!
I definitely referenced a bunch of stack overflow pages but I did not copy any code. Initially I copied some of my own code for the list item, but I completely rewrote that because it utilized a bunch of innefficient LinearLayouts.

I utilized Glide for the photo cacheing, specifically version 4.14.2. 

I used a creativecommons asset for the photo placeholder
https://creativecommons.org/licenses/by-sa/3.0/legalcode

## Is there any other information you’d like us to know?
I really enjoyed working on this project. Working on a legacy application it's easy to fall behind on modern best practices, so this was a much needed refresher.