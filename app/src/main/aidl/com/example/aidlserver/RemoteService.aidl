// RemoteService.aidl
package com.example.aidlserver;
import com.example.aidlserver.Candy;
// Declare any non-default types here with import statements

interface RemoteService {

    int sum(int a, int b);
    List<String> getData();
    List<Candy> getCandy();
}
