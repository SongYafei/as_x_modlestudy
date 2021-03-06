package com.lock.object.activity;

import android.os.Bundle;
import android.view.View;

import com.lock.object.custom.Custom;
import com.lock.object.reen.Reentrant;
import com.lock.object.sync.SynchronizedLock;
import com.lock.object.sync.SynchronizedLockSimple;
import com.yline.test.BaseTestActivity;

public class MainActivity extends BaseTestActivity {
    @Override
    public void testStart(View view, Bundle savedInstanceState) {
        // 单纯的 synchronized 关键词
        addButton("synchronized", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SynchronizedLockSimple.test("AA", 1500, 500, 500);
                SynchronizedLockSimple.test("BB", 1000, 3000, 500);
            }
        });

        // 单独测试 lock.wait
        addButton("testWait", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SynchronizedLock.testWait("wait forever", 500, -1, 500);
                SynchronizedLock.testWait("wait 3000ms", 500, 3000, 500);
            }
        });

        // 单独测试 lock.notify
        addButton("testNotify", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SynchronizedLock.testNotify("notify", 500, 500, 500, false);
                SynchronizedLock.testNotify("notifyAll", 500, 500, 500, true);
            }
        });

        // 混合测试 wait + notify
        addButton("testWaitAndNotify", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SynchronizedLock.testWait("wait AA", 200, -1, 800);
                SynchronizedLock.testWait("wait BB", 300, -1, 700);
                SynchronizedLock.testWait("wait CC", 400, -1, 600);

                SynchronizedLock.testNotify("notify", 1000, 1, 500, false);
            }
        });

        // 混合测试 wait + notifyAll
        addButton("testWaitAndNotifyAll", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SynchronizedLock.testWait("wait AA", 200, -1, 800);
                SynchronizedLock.testWait("wait BB", 300, -1, 700);
                SynchronizedLock.testWait("wait CC", 400, -1, 600);

                SynchronizedLock.testNotify("notify", 1000, 1, 3000, true);
            }
        });

        // 测试，自定义 锁；可重入锁含义
        addButton("testRepeatable", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Custom.testRepeatable();
            }
        });

        // 测试，自定义 锁；不可重入锁含义
        addButton("testNonRepeatable", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Custom.testNonRepeatable();
            }
        });

        // ReentrantLock简单测试
        addButton("testReentrantSimple", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reentrant.testSimple(100, 500, 200, 100);
            }
        });

        // ReentrantLock LockInterruptibly，没有实际效果
        addButton("", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reentrant.testLockInterruptibly(300, 200, 200, 300);
            }
        });
    }
}
