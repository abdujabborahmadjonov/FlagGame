package dev.abdujabbor.flaggame

import android.annotation.SuppressLint
import android.content.*
import android.graphics.Color
import android.os.Bundle
import android.renderscript.Sampler.Value
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewStructure.HtmlInfo
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.SnackbarLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
     var usedback :Boolean   = true
    val url = "https://t.me/abdujab_boR"
    private var backPressedTime = 0L
    lateinit var ListOfFlags: ArrayList<dev.abdujabbor.flaggame.Models.List>
    var count = 0
    var countryName = ""
    lateinit var buttonArrayList: ArrayList<Button>

    lateinit var layoutTxt: LinearLayout
    lateinit var LinearLayoutButtonOne: LinearLayout
    lateinit var LinearLayoutButtonSecond: LinearLayout
    lateinit var image: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonArrayList = ArrayList()

        layoutTxt = findViewById(R.id.answer)
        LinearLayoutButtonOne = findViewById(R.id.variant1)
        LinearLayoutButtonSecond = findViewById(R.id.variant2)
        image = findViewById(R.id.image_1)
        
        hintd.setOnClickListener{
           val a =  ListOfFlags[count].name?.toUpperCase()

            val start = a!!.subSequence(0,1)
            val end = a.subSequence(a.length-1,a.length)

            SnackView("Start:$start\n" +
                    "End:$end")
        }
        

        CreateObject()
        putButtonCount()





    }

    private fun CreateObject() {
        ListOfFlags = ArrayList()
        ListOfFlags.shuffle()
        ListOfFlags.add(dev.abdujabbor.flaggame.Models.List("portugaly", R.drawable.portugaly))
        ListOfFlags.add(dev.abdujabbor.flaggame.Models.List("hongkong", R.drawable.hongkong))
        ListOfFlags.add(dev.abdujabbor.flaggame.Models.List("singapore", R.drawable.singapore))
        ListOfFlags.add(dev.abdujabbor.flaggame.Models.List("france", R.drawable.france))
        ListOfFlags.add(dev.abdujabbor.flaggame.Models.List("colombia", R.drawable.colombia))
        ListOfFlags.add(dev.abdujabbor.flaggame.Models.List("india", R.drawable.india))
        ListOfFlags.add(dev.abdujabbor.flaggame.Models.List("brazil", R.drawable.brazil))
        ListOfFlags.add(dev.abdujabbor.flaggame.Models.List("turkey", R.drawable.turkey))
        ListOfFlags.add(dev.abdujabbor.flaggame.Models.List("ukraine", R.drawable.ukraine))
        ListOfFlags.add(dev.abdujabbor.flaggame.Models.List("italy", R.drawable.italy))
        ListOfFlags.add(dev.abdujabbor.flaggame.Models.List("spain", R.drawable.spain))
        ListOfFlags.add(dev.abdujabbor.flaggame.Models.List("canada", R.drawable.canada))
        ListOfFlags.add(dev.abdujabbor.flaggame.Models.List("latvia", R.drawable.latvia))
        ListOfFlags.add(dev.abdujabbor.flaggame.Models.List("uzbekistan", R.drawable.uzbekistan))
        ListOfFlags.add(dev.abdujabbor.flaggame.Models.List("pakistan", R.drawable.pakistan))
        ListOfFlags.add(dev.abdujabbor.flaggame.Models.List("japan", R.drawable.japan))
        ListOfFlags.add(dev.abdujabbor.flaggame.Models.List("albania", R.drawable.albania))
        ListOfFlags.add(dev.abdujabbor.flaggame.Models.List("croatia", R.drawable.croatia))
        ListOfFlags.add(dev.abdujabbor.flaggame.Models.List("denmark", R.drawable.denmark))

        ListOfFlags.shuffle()

    }

    fun putButtonCount() {
        image.setImageResource(ListOfFlags[count].image!!)
        layoutTxt.removeAllViews()
        LinearLayoutButtonOne.removeAllViews()
        LinearLayoutButtonSecond.removeAllViews()
        countryName = ""
        putButton(ListOfFlags[count].name)
    }

    private fun putButton(countryName: String?) {
        val btnArray: ArrayList<Button> = randomBtn(countryName)
        for (i in 0..5) {
            LinearLayoutButtonOne.addView(btnArray[i])
        }
        for (i in 6..11) {
            LinearLayoutButtonSecond.addView(btnArray[i])
        }
    }

    @SuppressLint("ResourceType")
    private fun randomBtn(countryName: String?): ArrayList<Button> {
        val array = ArrayList<Button>()
        val arrayText = ArrayList<String>()

        for (c in countryName!!) {
            arrayText.add(c.toString())
        }
        if (arrayText.size != 12) {
            val str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            for (i in arrayText.size until 12) {
                val random = Random().nextInt(str.length)
                arrayText.add(str[random].toString())
            }
        }
        arrayText.shuffle()

        for (i in 0 until arrayText.size) {
            val button = Button(this)
            button.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,


                1.0f

            )
            button.text = arrayText[i]
        button.setOnClickListener(this)
            array.add(button)
        }
        return array
    }

    override fun onClick(view: View?) {
        val button1 = view as Button
        if (buttonArrayList.contains(button1)) {
            layoutTxt.removeView(button1)
            var hasC = false
            LinearLayoutButtonOne.children.forEach { button ->
                if ((button as Button).text.toString() == button1.text.toString()) {
                    button.visibility = View.VISIBLE
                    countryName = countryName.substring(0, countryName.length - 1)
                    hasC = true
                }
            }
            LinearLayoutButtonSecond.children.forEach { button ->
                if ((button as Button).text.toString() == button1.text.toString()) {
                    button.visibility = View.VISIBLE
                    if (!hasC) {
                        countryName = countryName.substring(0, countryName.length - 1)
                    }
                }
            }

        }else{
            button1.visibility = View.INVISIBLE
            countryName += button1.text.toString().toUpperCase()
            val button2 = Button(this)
            button2.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f)
            button2.text = button1.text
            button2.setOnClickListener(this)
            buttonArrayList.add(button2)
            layoutTxt.addView(button2)
            correctAnswer()
        }
    }

    private fun correctAnswer() {
        if (countryName == ListOfFlags[count].name?.toUpperCase()){
            txtCheck.text = "Correct"
            txtCheck.setTextColor(Color.GREEN)
            if (count == ListOfFlags.size-1){
                count=0
            }else{
                count++
            }
            putButtonCount()
        }else{
            if (countryName.length == ListOfFlags[count].name?.length){
                txtCheck.text = "Wrong!"
                txtCheck.setTextColor(Color.parseColor("#BD0000"))
                layoutTxt.removeAllViews()
                LinearLayoutButtonSecond.removeAllViews()
                LinearLayoutButtonOne.removeAllViews()
                putButton(ListOfFlags[count].name)
                countryName = ""
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu,menu)


        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.about->{
                MaterialAlertDialogBuilder(this)
                    .setTitle("About").setMessage("This game will help you to know the flag of the countries. " +
                            "The game is much more useful for your brain.\n" +
                            "Telegram: @abdujab_boR")
                    .setNeutralButton("",object :DialogInterface.OnClickListener{


                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            showSnackbar("")
                        }

                    }).setNegativeButton("copy Link"){
                            dialog,which->
                        val clipboard = getSystemService(CLIPBOARD_SERVICE)as ClipboardManager

                        val clip = ClipData.newPlainText("EditText","t.me/Abdujab_boR")

                        clipboard.setPrimaryClip(clip)

                        Toast.makeText(this, "text copied", Toast.LENGTH_SHORT).show()

                    }
                    .setPositiveButton("yes"){
                            idalog,which->

                        showSnackbar("Thanks")
                    }.show()

            }

            R.id.exit->{
             AlertDialog.Builder(this)
                 .setTitle("Exit")
                 .setMessage("Do you want to exit").setCancelable(true)
                 .setPositiveButton("yes"){
                     dia,it->
                     finish()
                 }
                 .setNegativeButton("no"){
                     dia,it->
                     dia.cancel()
                 }.show()
            }

            R.id.share->{
                try {
                    val sharingIntent = Intent(Intent.ACTION_SEND)
                    sharingIntent.type = "text/plain"
                    val shareBody =
                        "Phone Number\n " +
                                "\n+998916563726"
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)






                    //change the app package id as your wish for sharing content to the specific one, WhatsApp's package id is com.whatsapp, and for facebook is com.facebook.katana
                    sharingIntent.setPackage("com.whatsapp")
                    startActivity(sharingIntent)
                } catch (ex: ActivityNotFoundException) {
                    val sharingIntent1 = Intent(Intent.ACTION_SEND)
                    sharingIntent1.type = "text/plain"
                    val shareBody =
                        "Phone Number \n \n+998916563726"
                    val shareSubject = "Abdujabbor Ahmadjonov"
                    sharingIntent1.putExtra(Intent.EXTRA_TEXT, shareBody)
                    sharingIntent1.putExtra(Intent.EXTRA_SUBJECT, shareSubject)
                    startActivity(Intent.createChooser(sharingIntent1, "Share with friends"))
                }
            }



        }

        return super.onOptionsItemSelected(item)
    }

    fun showSnackbar(msg:String){
        Snackbar.make(rootLayout,msg,Snackbar.LENGTH_SHORT).show()
    }

    fun SnackView(msg:String){
        Snackbar.make(lineHint,msg,Snackbar.ANIMATION_MODE_SLIDE).show()
        SnackbarLayout.TRANSLATION_Y
    }

    override fun onBackPressed() {
        if (backPressedTime+2000>System.currentTimeMillis()) {
            super.onBackPressed()
        }else{

            Toast.makeText(applicationContext, "Press back again to exit app", Toast.LENGTH_SHORT).show()

        }
        backPressedTime = System.currentTimeMillis()
        }

}