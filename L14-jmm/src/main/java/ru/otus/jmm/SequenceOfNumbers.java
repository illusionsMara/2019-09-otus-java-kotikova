package ru.otus.jmm;

import java.util.concurrent.TimeUnit;

public class SequenceOfNumbers {
    private static final String THREAD_1 = "Thread 1";
    private static final String THREAD_2 = "Thread TWO";
    private static final int MIN = 1;
    private static final int MAX = 10;
    private static final int ITERATIONS = 50;

    private final Object obj = new Object();
    private String activeThread = THREAD_1;

    public static void main(String[] args) throws InterruptedException {
        SequenceOfNumbers sequenceOfNumbers = new SequenceOfNumbers();
        sequenceOfNumbers.run();
    }

    private void run() throws InterruptedException {
        Thread thread1 = new Thread( this::printNumbers );
        thread1.setName( THREAD_1 );
        Thread thread2 = new Thread( this::printNumbers );
        thread2.setName( THREAD_2 );

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    private void printNumbers() {
        int i = 0;
        int number = MIN;   // устанавливаем для каждого потока начальное число в MIN
        boolean increase = (number == MIN);  // если начальное число установлено в MIN, значит первая фаза - инкремент до MAX

        while( i++ < ITERATIONS ) {
            while( increase ) {
                if( activeThread.equals( Thread.currentThread().getName() ) ) {
                    synchronized( obj ) {
                        System.out.println( activeThread + " : " + number );
                        if( number == MAX - 1 ) {
                            increase = false;
                        } else {
                            number++;
                        }
                        activeThread = THREAD_1.equals(activeThread) ? THREAD_2 : THREAD_1;
                    }
                }
            }
            number++;
            while( !increase ) {
                if( activeThread.equals( Thread.currentThread().getName() ) ) {
                    synchronized( obj ) {
                        System.out.println( activeThread + " : " + number );
                        if( number == MIN + 1 ) {
                            increase = true;
                        } else {
                            number--;
                        }
                        activeThread = THREAD_1.equals(activeThread) ? THREAD_2 : THREAD_1;
                    }
                }
            }
            number--;
        }
    }

    // sleep();  добавлять в код, чтобы цифры не мельтешили перед глазами
    private static void sleep() {
        try {
            Thread.sleep( TimeUnit.SECONDS.toMillis(1));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
