package com.example.smarthome.Ui.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.smarthome.Persent.DataPresenter;
import com.example.smarthome.Persent.MainPresenter;
import com.example.smarthome.R;
import com.example.smarthome.View.BaseActivity;
import com.example.smarthome.View.UiInterface;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by joel.
 * Date: 2019/6/26
 * Time: 9:16
 * Description:
 */
public class Aty_ShowTemData extends BaseActivity<UiInterface, DataPresenter<UiInterface>> implements UiInterface {


    TextView tv_which;
    TextView tv_avg;
    PieChartView piechar;
    LineChartView lineChartView;


    int Ymin;
    int Ymax;

    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
    List<AxisValue> mAxisYValues = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_showdata);
        initView();
        getPresenter().ReadTemperature("hzy");


    }

    @Override
    public DataPresenter<UiInterface> choicePresent() {
        return new DataPresenter();
    }



    private void initView() {
        tv_which=findViewById(R.id.tv_which);
        tv_avg=findViewById(R.id.tv_avg);
        piechar=findViewById(R.id.pie_chart);
        lineChartView=findViewById(R.id.line_chart);
    }


    private void initPicChart(int num) {

        //初始化数据
        List<SliceValue> values = new ArrayList<SliceValue>();
        SliceValue sliceValue;
        if (num > 50) {


            sliceValue = new SliceValue((float)num,Color.parseColor("#3fFF3030")).setLabel("");
            values.add(sliceValue);
            sliceValue = new SliceValue((float)100-num,Color.parseColor("#ffFF3030")).setLabel("");
            values.add(sliceValue);
        }else{
            sliceValue = new SliceValue((float)num, Color.parseColor("#ffFF3030")).setLabel("");
            values.add(sliceValue);
            sliceValue = new SliceValue((float)100-num,Color.parseColor("#3fFF3030")).setLabel("");
            values.add(sliceValue);
        }





        PieChartData data = new PieChartData(values);
        data.setHasLabels(true);

        data.setHasLabels(true);//显示表情
        data.setHasLabelsOnlyForSelected(false);//不用点击显示占的百分比
        data.setHasLabelsOutside(false);//占的百分比是否显示在饼图外面
        data.setHasCenterCircle(true);//是否是环形显示
        data.setValues(values);//填充数据
        data.setCenterCircleColor(Color.argb(0,0,0,0));//设置环形中间的颜色
        data.setCenterCircleScale(0.95f);//设置环形的大小级别
        data.setCenterText1("温度");
        data.setCenterText2(num+"°C");
        data.setCenterText2FontSize(25);
        data.setCenterText1Color(Color.WHITE);
        data.setCenterText2Color(Color.WHITE);
        data.setCenterText1FontSize(18);

        piechar.setPieChartData(data);
        piechar.setValueSelectionEnabled(true);//选择饼图某一块变大
        piechar.setAlpha(0.9f);//设置透明度
      //  piechar.setCircleFillRatio(1f);//设置饼图大小


        data.setSlicesSpacing(4);


        piechar.setPieChartData(data);
    }

    private void getAxisPoints(int[] score) {

        int last=score[0];
        for (int i = 0; i <score .length; i++) {
            if(i>15){
                break;
            }
            mPointValues.add(new PointValue(i, score[i]).setLabel(score[i]+" °C"));

            Ymax=score[i]>Ymax?score[i]:Ymax;
            Ymin=score[i]<Ymin?score[i]:Ymin;
            last=score[i];
        }
        Log.i("data", "getAxisPoints: "+Ymax+"  "+Ymin);
        for(int i = Ymin-5; i <=Ymax+5; i+= 1){
            mAxisYValues.add(new AxisValue(i).setLabel(i+" °C"));
        }
    }
    private void initLineChart() {
        Line line = new Line(mPointValues);  //折线的颜色（橙色）
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(true);//曲线是否平滑，即是曲线还是折线
        line.setStrokeWidth(1);
     //   line.setFilled(true);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
        line.setPointRadius(3);
//      line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        line.setColor(Color.parseColor("#ffFF6A6A"));
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(false);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.BLACK);  //设置字体颜色
        //axisX.setName("date");  //表格名称
        axisX.setTextSize(12);//设置字体大小
        axisX.setMaxLabelChars(8); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线

//        Axis axisY = new Axis();  //Y轴
//        axisY.setName("");//y轴标注
//        axisY.setTextSize(10);//设置字体大小
//        data.setAxisYLeft(axisY);  //Y轴设置在左边


        Axis axisY = new Axis().setHasLines(false);
        axisY.setTextColor(Color.BLACK);
        axisY.setLineColor(Color.BLACK);
        axisY.setMaxLabelChars(6);//max label length, for example 60
        axisY.setValues(mAxisYValues);
        data.setAxisYLeft(axisY);  //Y轴设置在左边


        //设置行为属性，支持缩放、滑动以及平移
        lineChartView.setInteractive(true);
        lineChartView.setZoomType(ZoomType.HORIZONTAL);
        lineChartView.setMaxZoom((float) 20);//最大方法比例
        lineChartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChartView.setLineChartData(data);
        lineChartView.setVisibility(View.VISIBLE);

//        固定Y軸高度，設置x軸的移動
        Viewport v = new Viewport(lineChartView.getMaximumViewport());
        v.top =Ymax+5;
        v.bottom = Ymin-5;
        lineChartView.setMaximumViewport(v);
        v.left = 0;
        v.right= 2;
        lineChartView.setCurrentViewport(v);

    }
    private void getAxisXLables(String[] date) {
        for (int i = 0; i < date.length; i++) {
            if(i>15){
                break;
            }
            mAxisXValues.add(new AxisValue(i).setLabel(date[i]));
        }

    }

    @Override
    public void ReadCo2(int[] values, String[] times) {

    }

    @Override
    public void ReadLedState(int i, String times) {

    }

    @Override
    public void ReadSmog(int[] values, String[] times) {

    }

    @Override
    public void ReadTemperature(int[] values, String[] times) {
        int avg=0;
        int i;
        for(i=0;i<values.length;i++){
            avg+=values[i];
        }
        avg/=i;
        tv_avg.setText("平均温度："+avg+"°C");
        tv_which.setText("室内温度");
        getAxisXLables(times);
        getAxisPoints(values);
        initLineChart();
        initPicChart(values[0]);
    }
}
