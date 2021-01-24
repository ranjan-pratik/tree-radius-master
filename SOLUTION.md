The solution contains following layers :
	1) Service Layer - all business logic
		a) A service class
		b) Utility/Helper class
	2) Rest Layer - to expose an endpoint for user I/O
	3) Domain Models - to hold/cache and move around the data
	
The Utility Class - 
	This class has the core logic to do cartesian calculations; to figure out if a given cartesian point (X,Y) lies within (or on) a given circle (P,Q,R - 	where 	P and Q are coordinates of the center and R is the radius). 
	The following formulae is used - 
		(((X-P)^2) + ((Y-Q)^2)) <= (R^2)
		
The Service is built out to filter over a stream of datapoints that lie within a given circle. The circle is formed from user input. The CartesianCircle class represents this user input and takes care of converting from meter to feet. (1 meter = 3.28 ft)

To enable one-time read of source data, a singleton bean has been created which is loaded with data on application-init. This acts as a cache for the raw data-points. The time complexity of the core service method becomes 2*(O(n)) ~ O(n)

TDD approach has been applied to create this solution, hence has a good coverage (EclEmma ~ 92%)

Area of extension would be to generalize the filtering mechanism - probably implement a strategy pattern to filter data-sets with different specifications.
