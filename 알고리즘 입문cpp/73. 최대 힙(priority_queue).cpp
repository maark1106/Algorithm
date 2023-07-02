#include<iostream>
#include<queue>
using namespace std;


int main() {
	
	int n;
	priority_queue<int> pQ;

	while (true) {
		cin >> n;
		if (n == -1)
			break;
		if (n == 0) {
			if (pQ.empty())
				cout << "-1" << "\n";
			else {
				cout << pQ.top() << "\n";
				pQ.pop();
			}
		}
		else
			pQ.push(n);			
	}
	
	return 0;
}