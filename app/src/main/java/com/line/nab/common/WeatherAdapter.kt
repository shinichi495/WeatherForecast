package com.line.nab.common

import com.line.nab.R

class WeatherAdapter : BaseAdapter<WeatherObj>(R.layout.item_layout) {
}
class WeatherObj(var date : String, var avg : String, var press : String, var hum : String, var des : String) : ListItemViewModel() {}