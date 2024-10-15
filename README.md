# brewtility

[![Clojars Project](https://img.shields.io/clojars/v/com.wallbrew/brewtility.svg)](https://clojars.org/com.wallbrew/brewtility)
[![cljdoc badge](https://cljdoc.org/badge/com.wallbrew/brewtility)](https://cljdoc.org/d/com.wallbrew/brewtility/CURRENT)
[![GitHub](https://img.shields.io/github/license/Wall-Brew-Co/brewtility)](https://github.com/Wall-Brew-Co/brewtility/blob/master/LICENSE)
[![Twitter Follow](https://img.shields.io/twitter/follow/WallBrew?style=social)](https://twitter.com/WallBrew)

A Clojure(Script) utility library for all of your brewing needs.

This repository follows the guidelines and standards of the [Wall Brew Open Source Policy.](https://github.com/Wall-Brew-Co/open-source "Our open source guidelines")

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [Installation](#installation)
- [Expectations](#expectations)
- [Provided Functionality](#provided-functionality)
- [Common Patterns](#common-patterns)
- [Testing](#testing)
- [Contributors](#contributors)
- [License](#license)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## Installation

A deployed copy of the most recent version of [brewtility can be found on clojars.](https://clojars.org/com.wallbrew/brewtility)
To use it, add the following as a dependency in your project.clj file:

[![Clojars Project](https://clojars.org/com.wallbrew/brewtility/latest-version.svg)](com.wallbrew/brewtility)

The next time you build your application, [Leiningen](https://leiningen.org/) or [deps.edn](https://clojure.org/guides/deps_and_cli) should pull it automatically.
Alternatively, you may clone or fork the repository to work with it directly.

## Expectations

This library is designed to work in conjunction with the [common-beer-format.](https://github.com/Wall-Brew-Co/common-beer-format)
The functions within the library make one main assumptions based on that fact:

1. All measurements are expected to be in the [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units), also known as the modern metric system. Since many brewing applications, especially those based in America, operate on Imperial Measurements, conversion utilities have been provided in the `brewtility.units` namespace.

## Provided Functionality

Below are examples of provided functionality for each namespace.
The library does provide access to other functions, but those primarily exist in support of those outlined here.

- [Calculations](doc/api/calculations.md)
- [Precision](doc/api/precision.md)
- [Predicates](doc/api/predicates.md)
- [Units of Measure](doc/api/units.md)
- [Wrapping](doc/api/wrapping.md)

## Common Patterns

Brewtility follows several conventions and design patterns that result in artifacts available to library consumers.
These are not strictly required for use; however, they provide additional documentation and ease-of-use to those who adopt them.

- [Symbolic Keywords](doc/patterns/symbolic_keywords.md)

## Testing

[doo](https://github.com/bensu/doo), a Leiningen plugin used to run ClojureScript tests in many JS environments, is already in `project.clj`.
[Karma](https://karma-runner.github.io/latest/index.html) is used as the test runner, and is included in `package.json`.

To install Karma, simply install the Node package:

```bash
npm install
```

Then build the application and run the tests:

```bash
lein test-build
```

The tests will also execute on the JVM, to ensure the library is compatible for apps in both deployment environments.

## Contributors

<a href="https://github.com/Wall-Brew-Co/brewtility/graphs/contributors"><img src="https://raw.githubusercontent.com/Wall-Brew-Co/brewtility/master/CONTRIBUTORS.svg" alt="The GitHub profile pictures of all current contributors. Clicking this image will lead you to the GitHub contribution graph." /></a>

## License

Copyright © [Wall Brew Co](https://wallbrew.com/)

This software is provided for free, public use as outlined in the [MIT License](https://github.com/Wall-Brew-Co/brewtility/blob/master/LICENSE)
