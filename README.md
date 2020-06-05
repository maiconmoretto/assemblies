# assemblies
 Voting system by assemblies
 
 
<p>Objective
<p>In cooperatives, each member has one vote and decisions are taken in assemblies,
by vote. Imagine that you must create a backend solution to manage these
voting.
<p>This solution must be executed in the cloud and promote the following functionalities through
a REST API:
<p>● Register a new agenda
<p>● Open a voting session on an agenda (the voting session must remain open for one
time determined in the opening call or 1 minute by default)
<p>● Receive votes from members on agendas (votes are only 'Yes' / 'No.'. Each member
is identified by a unique id and can only vote once per agenda)
<p>● Count the votes and give the result of the vote on the agenda

<p>For exercise purposes, the security of the interfaces can be abstracted and any call to the
interfaces can be considered as authorized. The choice of language, frameworks and
libraries is free (as long as it does not infringe use rights).
<p>It is important that the agendas and votes are persisted and that they are not lost with the restart
application.
<p>Bonus tasks
<p>● Bonus Task 1 - Integration with external systems
<p>○ Integrate with a system that verifies, from the member's CPF, if he can
vote
<p>■ GET https://user-info.herokuapp.com/users/{cpf}
<p>■ If the CPF is invalid, the API will return HTTP Status 404 (Not found).
You can use CPF generators to generate valid CPFs
<p>■ If the CPF is valid, the API will return if the user can (ABLE_TO_VOTE)
or cannot (UNABLE_TO_VOTE) perform the operation

<p>●Bonus Task 2 - Messaging and queues
<p>●○ The voting result needs to be reported to the rest of the platform, this
it should preferably be done through messaging. When the
voting close, post a message with the voting result

<p>●Bonus Task 3 - Performance
<p>○ Imagine that your application can be used in scenarios that have hundreds of
thousands of votes. She must behave in a performative way in these scenarios
<p>○ Performance tests are a good way to guarantee and observe how your
application behaves
<p>Bonus Task 4 - API Versioning
<p>○ How would you version your application's API? What strategy to use?
