package com.example.mobilenumbernames

import androidx.appcompat.app.AppCompatActivity
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.TextView
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatDelegate
import android.os.Handler
import android.media.MediaActionSound
import android.media.MediaPlayer
import android.media.VolumeAutomation
import android.widget.Switch
import android.graphics.Color
import android.content.Intent
import android.view.animation.Animation
import android.view.animation.AnimationUtils

import android.widget.Toast
import androidx.core.content.res.ResourcesCompat

class MainActivity : AppCompatActivity() {
    private var backPressedTime:Long = 0
    lateinit var backToast:Toast
    override fun onBackPressed() {
        backToast = Toast.makeText(this, "Press Again to Exit.", Toast.LENGTH_LONG)
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel()
            super.onBackPressed()
            
            val mp = MediaPlayer.create(this, R.raw.cursor)
            mp.stop()



            finish();
            finishAffinity();
            System.exit(0);
        }
        else {
            backToast.show()
        }
        backPressedTime = System.currentTimeMillis()
    }


    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val v = currentFocus
        if (v != null && (ev.action == MotionEvent.ACTION_UP || ev.action == MotionEvent.ACTION_MOVE)
            && v is EditText
            && !v.javaClass.name.startsWith("android.webkit.")
        ) {
            val scrcoords = IntArray(2)
            v.getLocationOnScreen(scrcoords)
            val x = ev.rawX + v.getLeft() - scrcoords[0]
            val y = ev.rawY + v.getTop() - scrcoords[1]
            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom()
            ) hideKeyboard(this)
        }
        return super.dispatchTouchEvent(ev)
    }

    fun hideKeyboard(activity: Activity?) {
        if (activity != null && activity.window != null && activity.window.decorView != null
        ) {
            val imm = activity
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(
                activity.window.decorView
                    .windowToken, 0
            )
        }
    }

    ////////////////////// Use Default Save sounds ////////////////////////

    //fun playBeepSound() {
    //    val sound = MediaActionSound()
    //    sound.play(MediaActionSound.START_VIDEO_RECORDING)
    //}

    //////////////////////////////////////////////////////////////////////

    fun playKeySound() {
        val mp = MediaPlayer.create(this, R.raw.key);
        mp.start()
    }

    fun playCursorSound() {
        val mp = MediaPlayer.create(this, R.raw.cursor);
        mp.stop()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)



        val word = findViewById(R.id.word) as EditText
        val wordToNumber = findViewById(R.id.wordToNumber) as TextView
        val button = findViewById(R.id.button) as Button
        val switch1 = findViewById(R.id.switch1) as Switch
        val gcb = findViewById(R.id.gcb) as TextView


        fun animation() {
            gcb.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));
        }

        val shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            gcb.startAnimation(shake);

        gcb.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));


        button.setEnabled(false)

        button.setVisibility(View.INVISIBLE)

        //wordToNumber.setTypeface(ResourcesCompat.getFont(context, R.font.aura-regular2))

        word.requestFocus()

        button.setOnClickListener {
            val work1 = word.getText().toString()
            val work = work1.toLowerCase()
            val number0 = work
            val number1 = number0
                .replace('a', '2')
                .replace('b', '2')
                .replace('c', '2')
                .replace('d', '3')
                .replace('e', '3')
                .replace('f', '3')
                .replace('g', '4')
                .replace('h', '4')
                .replace('i', '4')
                .replace('j', '5')
                .replace('k', '5')
                .replace('l', '5')
                .replace('m', '6')
                .replace('n', '6')
                .replace('o', '6')
                .replace('p', '7')
                .replace('q', '7')
                .replace('r', '7')
                .replace('s', '7')
                .replace('t', '8')
                .replace('u', '8')
                .replace('v', '8')
                .replace('w', '9')
                .replace('x', '9')
                .replace('y', '9')
                .replace('z', '9')
                .replace(' ', '0')
                .replace('.', '1')
            //wordToNumber.createFromAsset(getAssets(), "fonts/aura-regular2.ttf")
            wordToNumber.setText("$number1")
            //textForSound.setText("$number1")
            //playBeepSound()



        }

        ///////////////////////// Real Time Strind to Text View (button perform Click)  ///////////////////////

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                button.performClick()
                //button2.performClick()
                handler.postDelayed(this, 1)//1 sec delay
            }
        }, 0)

        val handler2 = Handler()
        handler2.postDelayed(object : Runnable {
            override fun run() {
                playCursorSound()
                animation()
                //button2.performClick()
                handler2.postDelayed(this, 1000)//1 sec delay
            }
        }, 0)



        ///////////////////////// Real Time Strind to Text View (button perform Click)  ///////////////////////


        ///////////////////////// Real Time Edit Text To Text View Code ///////////////////////

        val textWatcher = object : TextWatcher {
          override fun afterTextChanged(s: Editable?) {
         }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            //wordToNumber2.text = s    // TEXT VIEW
            //playKeySound()
           //if (start == 12) {
           //   Toast.makeText(applicationContext, "Maximum Limit Reached", Toast.LENGTH_SHORT)
           //     .show()
        //}
        }
        }
        word.addTextChangedListener(textWatcher) // EDIT TEXT

        ///////////////////////// Real Time Edit Text To Text View Code ///////////////////////






        word.setOnKeyListener { _, keyCode, keyEvent ->

            //if (keyCode == KeyEvent.KEYCODE_A || keyCode == KeyEvent.KEYCODE_B || keyCode == KeyEvent.KEYCODE_C || keyCode == KeyEvent.KEYCODE_D || keyCode == KeyEvent.KEYCODE_E || keyCode == KeyEvent.KEYCODE_F || keyCode == KeyEvent.KEYCODE_G || keyCode == KeyEvent.KEYCODE_H || keyCode == KeyEvent.KEYCODE_I || keyCode == KeyEvent.KEYCODE_J || keyCode == KeyEvent.KEYCODE_K || keyCode == KeyEvent.KEYCODE_L || keyCode == KeyEvent.KEYCODE_M || keyCode == KeyEvent.KEYCODE_N || keyCode == KeyEvent.KEYCODE_O || keyCode == KeyEvent.KEYCODE_P || keyCode == KeyEvent.KEYCODE_Q || keyCode == KeyEvent.KEYCODE_R || keyCode == KeyEvent.KEYCODE_S || keyCode == KeyEvent.KEYCODE_T || keyCode == KeyEvent.KEYCODE_U || keyCode == KeyEvent.KEYCODE_V || keyCode == KeyEvent.KEYCODE_W  || keyCode == KeyEvent.KEYCODE_X || keyCode == KeyEvent.KEYCODE_Y || keyCode == KeyEvent.KEYCODE_Z  || keyCode == KeyEvent.KEYCODE_PERIOD  || keyCode == KeyEvent.KEYCODE_SPACE  || keyCode == KeyEvent.KEYCODE_DEL || keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {

            if (keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                button.performClick()


                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }


        //switch1.setOnCheckedChangeListener ( compoundButton, onSwitch ->

        //if (onSwitch)
        //    switch1.setBackgroundColor (Color.GREEN)

        //else
        //    switch1.setBackgroundColor (Color.RED)

        //)

        switch1.setOnCheckedChangeListener { switch1, isChecked ->
            if (isChecked) {
                // The switch is enabled/checked
                //switch1.setBackgroundColor (Color.GREEN)
                val mp = MediaPlayer.create(this, R.raw.cursor)

                val handler2 = Handler()
                handler2.postDelayed(object : Runnable {
                    override fun run() {
                        mp.start()
                        //button2.performClick()
                        handler2.postDelayed(this, 1000)//1 sec delay
                    }
                }, 0)

                val textWatcher = object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        //wordToNumber2.text = s    // TEXT VIEW
                        playKeySound()
                        //if (start == 12) {
                        //   Toast.makeText(applicationContext, "Maximum Limit Reached", Toast.LENGTH_SHORT)
                        //     .show()
                        //}
                    }
                }
                word.addTextChangedListener(textWatcher)


                // Change the app background color
                //root_layout.setBackgroundColor(Color.GREEN)
            } else {
                // The switch is disabled
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                //intent.putExtra("ggwp", "$StringBuilder")

                startActivity(intent)

                finish();
                finishAffinity();

                System.exit(0)

                // Set the app background color to light gray
                //root_layout.setBackgroundColor(Color.LTGRAY)
            }
        }



    }
}