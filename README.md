# AutomationPractice

[![Build Status](https://61037293ad05.ngrok.io/buildStatus/icon?job=chromeTests&subject=chrome)](https://61037293ad05.ngrok.io/job/chromeTests/)

[![Build Status](https://61037293ad05.ngrok.io/buildStatus/icon?job=firefoxTests&subject=firefox)](https://61037293ad05.ngrok.io/job/firefoxTests/)

[![Build Status](https://61037293ad05.ngrok.io/buildStatus/icon?job=edgeTests&subject=edge)](https://61037293ad05.ngrok.io/job/edgeTests/)

[![Build Status](https://61037293ad05.ngrok.io/buildStatus/icon?job=operaTests&subject=opera)](https://61037293ad05.ngrok.io/job/operaTests/)

[![Build Status](https://61037293ad05.ngrok.io/buildStatus/icon?job=ieTests&subject=ie)](https://61037293ad05.ngrok.io/job/ieTests/)

AutomationPractice is a project that I've started while learning automation testing.

I'm using https://demo-teo.herokuapp.com/ - a demo website for e-commerce - to practice my magic.

What I've used: Selenium with Java, jUnit, Maven, Jenkins

I like to keep things simple and develop them as needed.

Features:
- browsers configured using Factory pattern: Chrome, Firefox, Opera, Edge, IE
- page object pattern for separating actual tests from their methods
- take screenshots when tests fail using Test Rules (currently used only in one of the test classes)
- Jenkins is integrated with GitHub and tests are triggered at each push
- Maven projects configured in Jenkins for each browser

Currently working on:
- exploring TestNG for test reporting