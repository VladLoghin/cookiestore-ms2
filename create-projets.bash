#!/usr/bin/env bash

spring init \
--boot-version=3.0.2 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=cart-subdomain \
--package-name=com.cookiestore.cartsubdomain \
--groupId=com.cookiestore.cartsubdomain \
--dependencies=web \
--version=1.0.0-SNAPSHOT \
cart-subdomain

spring init \
--boot-version=3.0.2 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=clients-subdomain \
--package-name=com.cookiestore.clientssubdomain \
--groupId=com.cookiestore.clientssubdomain \
--dependencies=web \
--version=1.0.0-SNAPSHOT \
clients-subdomain

spring init \
--boot-version=3.0.2 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=cookie-subdomain \
--package-name=com.cookiestore.cookiesubdomain \
--groupId=com.cookiestore.cookiesubdomain \
--dependencies=web \
--version=1.0.0-SNAPSHOT \
cookie-subdomain

spring init \
--boot-version=3.0.2 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=api-gateway \
--package-name=com.cookiestore.apigateway \
--groupId=com.cookiestore.apigateway \
--dependencies=web \
--version=1.0.0-SNAPSHOT \
api-gateway

