<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - SportFlow</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="flex items-center justify-center min-h-screen bg-gray-100">

<div class="w-full max-w-md p-6 bg-white rounded-lg shadow-lg">
    <h2 class="text-2xl font-bold text-center text-gray-900 mb-6">Welcome to SportFlow</h2>

    <!-- Submit the form directly to the LoginServlet -->
    <form action="LoginServlet" method="post" class="max-w-md mx-auto">
        <div class="relative z-0 w-full mb-5 group">
            <input type="email" name="email" id="email" class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer" placeholder=" " required />
            <label for="email" class="peer-focus:font-medium absolute text-sm text-gray-500 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Email</label>
        </div>

        <div class="relative z-0 w-full mb-5 group">
            <input type="password" name="motDePasse" id="motDePasse" class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer" placeholder=" " required />
            <label for="motDePasse" class="peer-focus:font-medium absolute text-sm text-gray-500 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Mot de passe</label>
        </div>

        <button type="submit" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center">Login</button>
    </form>

    <!-- Error message display (if any) -->
    <p class="mt-4 text-center text-gray-600">
        Don't have an account? <a href="#" class="text-blue-500 hover:underline">Sign Up</a>
    </p>

    <div class="text-red-500 text-center mt-4">
        <%= request.getParameter("error") != null ? "Invalid email or password!" : "" %>
    </div>
</div>

</body>
</html>
