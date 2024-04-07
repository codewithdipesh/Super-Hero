package com.example.superhero.Model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class Hero(
  @StringRes val nameRes:Int,
  @StringRes val descriptionRes:Int,
  @DrawableRes val imageRes:Int
) {

}