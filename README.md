# AndroidStudioProjects
android projects changed from eclipse to androidstudio

Problem:
Try to run under new sdk 23, android studio will show error msg listed below:
Error:failed to find Build Tools revision 23.0.0 rc2
<a href="install.build.tools">Install Build Tools 23.0.0 rc2 and sync project</a>


Answer:
1. change 23.0.0.rc2 to 23.0.1(or other your installed sdk version) in app->build.gradle
2. tools->android->sync project with gridle files

Done! you can run the samples codes again
