#include<iostream>

using namespace std;

int removeDuplicates( int A[], int n )
{
	if( n == 0 ) return 0;

	int p = 0;
	for( int i = 0; i < n - 1; i++ )
	{
		if( A[i] != A[i + 1] )
		{
			A[p++] = A[i];
		}
	}

	A[p] = A[n - 1];

	return p + 1;

	/*if( n == 0 ) return 0;

	int index = 0;
	for( int i = 1; i < n; i++ )
	{
		if( A[i] != A[index] )
			A[++index] = A[i];
	}

	return index + 1;*/
}





int main()
{
	int n;
	while( cin >> n )
	{
		int *arr = new int[n];
		for( int i = 0; i < n; i++ )
			cin >> arr[i];

		int x = removeDuplicates( arr, n );

		cout << x << endl;
		for( int i = 0; i < x; i++ )
			cout << arr[i] << " ";

		cout << endl;
		delete arr;
		arr = NULL;
	}
	return 0;
}
