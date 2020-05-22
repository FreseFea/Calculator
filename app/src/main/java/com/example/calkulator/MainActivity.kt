package com.example.calkulator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import net.objecthunter.exp4j.operator.Operator


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_0.setOnClickListener{setTextFields("0")}
        btn_1.setOnClickListener{setTextFields("1")}
        btn_2.setOnClickListener{setTextFields("2")}
        btn_3.setOnClickListener{setTextFields("3")}
        btn_4.setOnClickListener{setTextFields("4")}
        btn_5.setOnClickListener{setTextFields("5")}
        btn_6.setOnClickListener{setTextFields("6")}
        btn_7.setOnClickListener{setTextFields("7")}
        btn_8.setOnClickListener{setTextFields("8")}
        btn_9.setOnClickListener{setTextFields("9")}
        minus_btn.setOnClickListener{setTextFields("-")}
        plus_btn.setOnClickListener{setTextFields("+")}
        mult_btn.setOnClickListener{setTextFields("*")}
        desivion_btn.setOnClickListener{setTextFields("/")}
        bracket_open_btn.setOnClickListener{setTextFields("(")}
        bracket_close_btn.setOnClickListener{setTextFields(")")}
        clear_btn.setOnClickListener{
            math_operation.text = ""
            result_text.text = ""
        }
        back_btn.setOnClickListener{
            val str = math_operation.text.toString()
            if(str.isNotEmpty()) math_operation.text = str.substring(0,str.length -1)
            result_text.text = "" }


        <dependency>
        <groupId>net.objecthunter</groupId>
        <artifactId>exp4j</artifactId>
        <version>0.4.8</version>
        </dependency>


        val e: Expression = ExpressionBuilder("3 * sin(y) - 2 / (x - 2)")
            .variables("x", "y")
            .build()
            .setVariable("x", 2.3)
            .setVariable("y", 3.14)
        val result: Double = e.evaluate()


        val exec: ExecutorService = Executors.newFixedThreadPool(1)
        val e: Expression = ExpressionBuilder("3log(y)/(x+1)")
            .variables("x", "y")
            .build()
            .setVariable("x", 2.3)
            .setVariable("y", 3.14)
        val future: Future<Double> = e.evaluateAsync(exec)
        val result: Double = future.get()


        val result = ExpressionBuilder("2cos(xy)")
            .variables("x", "y")
            .build()
            .setVariable("x", 0.5)
            .setVariable("y", 0.25)
            .evaluate()
        assertEquals(2.0 * Math.cos(0.5 * 0.25), result, 0.0)


        val expr = "pi+π+e+φ"
        val expected = 2 * Math.PI + Math.E + 1.61803398874
        val e: Expression = ExpressionBuilder(expr).build()
        assertEquals(expected, e.evaluate(), 0.0)


        val expr = "7.2973525698e-3"
        val expected = expr.toDouble()
        val e: Expression = ExpressionBuilder(expr).build()
        assertEquals(expected, e.evaluate(), 0.0)


        val logb: Function = object : Function("logb", 2) {
            fun apply(vararg args: Double): Double {
                return Math.log(args[0]) / Math.log(args[1])
            }
        }
        val result = ExpressionBuilder("logb(8, 2)")
            .function(logb)
            .build()
            .evaluate()
        val expected = 3.0
        assertEquals(expected, result, 0.0)


        val avg: Function = object : Function("avg", 4) {
            fun apply(vararg args: Double): Double {
                var sum = 0.0
                for (arg in args) {
                    sum += arg
                }
                return sum / args.size
            }
        }
        val result = ExpressionBuilder("avg(1,2,3,4)")
            .function(avg)
            .build()
            .evaluate()

        val expected = 2.5
        assertEquals(expected, result, 0.0)


        val factorial: Operator = object :
            Operator(
                "!",
                1,
                true,
                PRECEDENCE_POWER + 1
            ) {
            override fun apply(vararg args: Double): Double {
                val arg = args[0].toInt()
                require(arg.toDouble() == args[0]) { "Operand for factorial has to be an integer" }
                require(arg >= 0) { "The operand of the factorial can not be less than zero" }
                var result = 1.0
                for (i in 1..arg) {
                    result *= i.toDouble()
                }
                return result
            }
        }


        val result = ExpressionBuilder("3!")
            .operator(factorial)
            .build()
            .evaluate()

        val expected = 6.0
        assertEquals(expected, result, 0.0)


        @Test
        public void testOperators3() throws Exception {
            Operator gteq = new Operator(">=", 2, true, Operator.PRECEDENCE_ADDITION - 1) {

                @Override
                public double apply(double[] values) {
                    if (values[0] >= values[1]) {
                        return 1d;
                    } else {
                        return 0d;
                    }
                }
            };

            Expression e = new ExpressionBuilder("1>=2").operator(gteq)
                .build();
            assertTrue(0d == e.evaluate());
            e = new ExpressionBuilder("2>=1").operator(gteq)
                .build();
            assertTrue(1d == e.evaluate());




    }
    fun setTextFields(str:String){
        math_operation.append(str)
    }



}
