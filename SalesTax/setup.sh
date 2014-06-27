APP_HOME="/home/kashifu/SalesTax"
echo "setting up classpaths"
export CLASSPATH=$CLASSPATH:$APP_HOME/lib/commons-lang3-3.3.2.jar
export CLASSPATH=$CLASSPATH:$APP_HOME/lib/junit-4.10.jar
export CLASSPATH=$CLASSPATH:$APP_HOME/lib/mockito-all-1.9.5.jar
echo "setting up binaries"
rm -rf $APP_HOME/bin/*

javac $APP_HOME/src/com/salestax/businessobjects/Currency.java -d $APP_HOME/bin
javac $APP_HOME/src/com/salestax/businessobjects/ItemOrigin.java -d $APP_HOME/bin
javac $APP_HOME/src/com/salestax/businessobjects/ItemType.java -d $APP_HOME/bin
javac $APP_HOME/src/com/salestax/util/ValidationHelper.java -d $APP_HOME/bin
javac $APP_HOME/src/com/salestax/businessobjects/Price.java -d $APP_HOME/bin
javac $APP_HOME/src/com/salestax/businessobjects/Item.java -d $APP_HOME/bin
javac $APP_HOME/src/com/salestax/businessobjects/ItemReceiptEntry.java -d $APP_HOME/bin
javac $APP_HOME/src/com/salestax/businessobjects/Receipt.java -d $APP_HOME/bin
javac $APP_HOME/src/com/salestax/businesslogic/TaxManager.java -d $APP_HOME/bin
javac $APP_HOME/src/com/salestax/businesslogic/ReceiptCalculator.java -d $APP_HOME/bin
javac $APP_HOME/src/com/salestax/businesslogic/ReceiptPrinter.java -d $APP_HOME/bin
javac $APP_HOME/src/com/salestax/businesslogic/ReceiptPrintTest.java -d $APP_HOME/bin
echo "done"

java $APP_HOME/bin/com/salestax/businesslogic/ReceiptPrintTest
