package net.taraabar.network.di

import net.taraabar.network.di.models.BaseUrlModel


interface IURLProvider {
    fun getURLS(): Set<BaseUrlModel>
}