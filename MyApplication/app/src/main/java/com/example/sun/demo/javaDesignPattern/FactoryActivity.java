package com.example.sun.demo.javaDesignPattern;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.sun.demo.R;
import com.example.sun.demo.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by sun on 17/8/11.
 */

public class FactoryActivity extends BaseActivity implements View.OnClickListener {
    private final int MESSAGE_WHAT_BMW = 1000;
    private final int MESSAGE_WHAT_PORSCHE = 1001;
    private final int MESSAGE_WHAT_FAEEARI = 1002;
    StringBuilder stringBuilderBMW = new StringBuilder();
    StringBuilder stringBuilderPORSCHE = new StringBuilder();
    StringBuilder stringBuilderFAEEARI = new StringBuilder();
    @Bind(R.id.tv_description)
    TextView mDescription;
    @Bind(R.id.tv_simple_factory)
    TextView mSimpleFactory;
    @Bind(R.id.tv_factory_method)
    TextView mFactoryMethod;
    @Bind(R.id.tv_abstract_factory)
    TextView mAbstractFactory;

    @Override
    public void setContent() {
        setContentView(R.layout.factory_activity);
    }

    @Override
    public void setModel() {

    }

    @Override
    public void setupView() {
        mSimpleFactory.setOnClickListener(this);
        mFactoryMethod.setOnClickListener(this);
        mAbstractFactory.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_simple_factory:
                stringBuilderBMW.delete(0,stringBuilderBMW.length());
                stringBuilderBMW.append("简单工厂模式：");
                simpleFactoryTest();
                handler.sendEmptyMessage(MESSAGE_WHAT_BMW);
                break;
            case R.id.tv_factory_method:
                stringBuilderPORSCHE.delete(0,stringBuilderPORSCHE.length());
                stringBuilderPORSCHE.append("工厂方法模式：");
                factoryMethodTest();
                handler.sendEmptyMessage(MESSAGE_WHAT_PORSCHE);
                break;
            case R.id.tv_abstract_factory:
                stringBuilderFAEEARI.delete(0,stringBuilderFAEEARI.length());
                stringBuilderFAEEARI.append("抽象工厂模式：");
                AbstractFactoryTest();
                handler.sendEmptyMessage(MESSAGE_WHAT_FAEEARI);
                break;
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!TextUtils.isEmpty(stringBuilderBMW) && msg.what == MESSAGE_WHAT_BMW) {
                mDescription.setText("");
                mDescription.setText(stringBuilderBMW);
            }else if (!TextUtils.isEmpty(stringBuilderPORSCHE ) && msg.what == MESSAGE_WHAT_PORSCHE ) {
                mDescription.setText("");
                mDescription.setText(stringBuilderPORSCHE );
            }else if (!TextUtils.isEmpty(stringBuilderFAEEARI) && msg.what == MESSAGE_WHAT_FAEEARI) {
                mDescription.setText("");
                mDescription.setText(stringBuilderFAEEARI);
            }
        }
    };

    /**
     * 简单工厂模式
     * 1) 工厂类角色：这是本模式的核心，含有一定的商业逻辑和判断逻辑，用来创建产品  如：Factory
     * 2) 抽象产品角色：它一般是具体产品继承的父类或者实现的接口。  如： BMW
     * 3) 具体产品角色：工厂类所创建的对象就是此角色的实例。在java中由一个具体类实现。如 BMW520  BMW521
     */
    abstract class BMW {
        public BMW() {
        }
    }

    public class BMW520 extends BMW {
        public BMW520() {
            stringBuilderBMW.append("生产BMW520,");
        }
    }

    public class BMW521 extends BMW {
        public BMW521() {
            stringBuilderBMW.append("生产BMW521");
        }
    }

    public class Factory {
        public BMW creatBMW(int type) {
            switch (type) {
                case 520:
                    return new BMW520();
                case 521:
                    return new BMW521();
                default:
                    break;
            }
            return null;
        }
    }

    private void simpleFactoryTest() {
        Factory factory = new Factory();
        factory.creatBMW(520);
        factory.creatBMW(521);
    }

    /**
     * 工厂方法模式
     * <p>
     * 1)抽象工厂角色： 这是工厂方法模式的核心，它与应用程序无关。是具体工厂角色必须实现的接口或者必须继承的父类。在java中它由抽象类或者接口来实现。
     * 2)具体工厂角色：它含有和具体业务逻辑有关的代码。由应用程序调用以创建对应的具体产品的对象。
     * 3)抽象产品角色：它是具体产品继承的父类或者是实现的接口。在java中一般有抽象类或者接口来实现。
     * 4)具体产品角色：具体工厂角色所创建的对象就是此角色的实例。在java中由具体的类来实现。
     */

    abstract class PORSCHE {
        public PORSCHE() {
        }
    }

    public class PORSCHE520 extends PORSCHE {
        public PORSCHE520() {
            stringBuilderPORSCHE.append("生成保时捷520，");
        }
    }

    public class PORSCHE521 extends PORSCHE {
        public PORSCHE521() {
            stringBuilderPORSCHE.append("生成保时捷521");
        }
    }

    interface FactoryPORSCHE {
        PORSCHE create();
    }

    public class FactoryPORSCHE520 implements FactoryPORSCHE {

        @Override
        public PORSCHE520 create() {
            return new PORSCHE520();
        }
    }

    public class FactoryPORSCHE521 implements FactoryPORSCHE {

        @Override
        public PORSCHE521 create() {
            return new PORSCHE521();
        }
    }

    private void factoryMethodTest() {
        FactoryPORSCHE520 factoryPORSCHE520 = new FactoryPORSCHE520();
        PORSCHE520 porsche520 = factoryPORSCHE520.create();

        FactoryPORSCHE521 factoryPORSCHE521 = new FactoryPORSCHE521();
        PORSCHE521 porsche521 = factoryPORSCHE521.create();
    }


    /**
     * 抽象工厂模式
     *
     * 抽象工厂模式是工厂方法模式的升级版本，他用来创建一组相关或者相互依赖的对象
     */

    //发动机以及型号
    abstract class Engine {

    }

    public class EngineA extends Engine {
        public EngineA() {
            stringBuilderFAEEARI.append("制造-->型号A的发动机");
        }
    }

    public class EngineB extends Engine {
        public EngineB() {
            stringBuilderFAEEARI.append("制造-->型号B的发动机");
        }
    }

    //空调以及型号
    abstract class Aircondition {

    }

    public class AirconditionA extends Aircondition {
        public AirconditionA() {
            stringBuilderFAEEARI.append("制造-->型号A的空调");
        }
    }

    public class AirconditionB extends Aircondition {
        public AirconditionB() {
            stringBuilderFAEEARI.append("制造-->型号B的空调");
        }
    }

    //创建工厂的接口
     interface AbstractFactory {
        //制造发动机
         Engine createEngine();
        //制造空调
         Aircondition createAircondition();
    }


    public class FactoryFAEEARI520 implements AbstractFactory{

        @Override
        public Engine createEngine() {
            return new EngineA();
        }
        @Override
        public Aircondition createAircondition() {
            return new AirconditionA();
        }
    }
    public class FactoryFAEEARI521 implements AbstractFactory {

        @Override
        public Engine createEngine() {
            return new EngineB();
        }
        @Override
        public Aircondition createAircondition() {
            return new AirconditionB();
        }
    }

    private void AbstractFactoryTest(){
        FactoryFAEEARI520 factoryFAEEARI520 = new FactoryFAEEARI520();
        factoryFAEEARI520.createEngine();
        factoryFAEEARI520.createAircondition();
        FactoryFAEEARI521 factoryFAEEARI521 = new FactoryFAEEARI521();
        factoryFAEEARI521.createEngine();
        factoryFAEEARI521.createAircondition();

    }

}
