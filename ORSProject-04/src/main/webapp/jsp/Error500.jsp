<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>500 - Internal Server Error</title>

  <style>
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }

    body {
      height: 100vh;
      display: flex;
      align-items: center;
      justify-content: center;
      background: #0f172a;
      color: #fff;
      font-family: Arial, sans-serif;
      overflow: hidden;
    }

    .container {
      text-align: center;
      z-index: 2;
      padding: 20px;
    }

    .error-code {
      font-size: 120px;
      font-weight: bold;
      color: #38bdf8;
      text-shadow: 0 0 20px rgba(56, 189, 248, 0.7);
    }

    .title {
      font-size: 32px;
      margin-top: 10px;
    }

    .description {
      margin-top: 15px;
      color: #cbd5e1;
      font-size: 18px;
      max-width: 500px;
      line-height: 1.6;
    }

    .btn {
      display: inline-block;
      margin-top: 30px;
      padding: 14px 28px;
      border-radius: 10px;
      background: #38bdf8;
      color: #0f172a;
      text-decoration: none;
      font-weight: bold;
      transition: 0.3s ease;
    }

    .btn:hover {
      background: #0ea5e9;
      transform: translateY(-2px);
    }

    .bg-circle {
      position: absolute;
      border-radius: 50%;
      background: rgba(56, 189, 248, 0.08);
      animation: float 10s infinite ease-in-out;
    }

    .circle1 {
      width: 300px;
      height: 300px;
      top: -100px;
      left: -100px;
    }

    .circle2 {
      width: 400px;
      height: 400px;
      bottom: -150px;
      right: -150px;
      animation-delay: 2s;
    }

    @keyframes float {
      0%, 100% {
        transform: translateY(0px);
      }
      50% {
        transform: translateY(20px);
      }
    }

    @media (max-width: 600px) {
      .error-code {
        font-size: 80px;
      }

      .title {
        font-size: 24px;
      }

      .description {
        font-size: 16px;
      }
    }
  </style>
</head>

<body>

  <div class="bg-circle circle1"></div>
  <div class="bg-circle circle2"></div>

  <div class="container">
    <div class="error-code">500</div>

    <div class="title">
      Internal Server Error
    </div>

    <p class="description">
      Something went wrong on our end.  
      We're already working to fix the issue.
    </p>

    <a href="/" class="btn">
      Go Back Home
    </a>
  </div>

</body>
</html>
