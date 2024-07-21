# Image-Comparison-Slider

Demo: 

![Demo](https://media.giphy.com/media/j1sdApf7nJOTvXoKST/giphy.gif)

To Use: 
        
Add following in build.gradle at module level.
   
        implementation 'com.swoopzi:image-comparison-slider:0.1.0'

Sample Usage:

        <com.swoopzi.imagecompareslider.ImageCompareSlider
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent"
                app:background_image="@drawable/ic_launcher_background"
                app:foreground_image="@drawable/ic_launcher_foreground"
                app:slider_icon="@drawable/ic_baseline_help_24"/>
