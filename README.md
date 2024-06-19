# VComponents

This is a library that implement components that can be easily resized with the frame

## Instalation

To install this librairy, first you have to clone the repository:
```shell
git clone https://github.com/SamuelVedel/VComponent.git
```
Then you have to compile sources:
```shell
make build
```
And finaly you install the compiled code with
```shell
INSTALL_PATH=<path> make install
```
where `<path>` is replaced by the place where you want to install the library, be careful the path need to end at an **existing** directory. If the pass is `~/javalibs/vcomponent` you can just use the command `make install`.

You can also create an jar file with the command
```shell
make jar
```
and then do whatever you want with the jar file.

## Utilisation

It is possible to generate a documentation with the command
```shell
make doc
```
That generate a `doc` directory with the documentation in. To open the documentation, we have to open `doc/index.html`, for exemple:
```shell
firefox doc/index.html
```
