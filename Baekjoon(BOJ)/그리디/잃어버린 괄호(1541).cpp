#include<bits/stdc++.h>

using namespace std;

int main() {

	string arr;
	cin >> arr;

	int res = 0;
	bool isMinus = false;
	string num = "";

	for (int i = 0; i <= arr.size(); i++) {
		
		if (arr[i] == '+' || arr[i] == '-' || i == arr.size()) {
			if (isMinus) 
				res -= stoi(num);
			else 
				res += stoi(num);			

			num = "";

			if (arr[i] == '-')
				isMinus = true;
		}
		else 
			num += arr[i];

	}

	cout << res;

	return 0;
}